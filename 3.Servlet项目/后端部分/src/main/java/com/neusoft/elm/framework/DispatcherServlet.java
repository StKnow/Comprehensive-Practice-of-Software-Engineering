package com.neusoft.elm.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //中文编码处理
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");


        String path = request.getServletPath();//获取servlet请求路径

        //根据请求路径，将控制器类名和方法名解析出来
        String className = path.substring(1, path.lastIndexOf("/"));
        String methodName = path.substring(path.lastIndexOf("/") + 1);

        PrintWriter out = null;//向客户端相应的输出流

        try {
            //通过Controller类全名获取此类的所有信息
            Class clazz = Class.forName("com.neusoft.elm.controller." + className);

            Object controller = clazz.newInstance();//创建Controller类的对象
            Method method = clazz.getMethod(methodName, new Class[]{HttpServletRequest.class});//获取Controller中的方法
            Object result = method.invoke(controller, new Object[]{request});//调用获取的方法
            System.out.println(result);
            //获取向客户端响应的输出流(jackson包）
            out = response.getWriter();
            ObjectMapper om = new ObjectMapper();
            out.print(om.writeValueAsString(result));//把Java对象解析成json格式数据向客户端响应

        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("DispatcherServlet信息：请求url：" + path);
            System.out.println("DispatcherServlet信息：类名：" + className + "\t方法名：" + methodName);
        } finally {
            out.close();
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
 