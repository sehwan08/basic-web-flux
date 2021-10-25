package com.cos.fluxtest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class MyFilter implements Filter{
	
	private EventNotify eventNotify;
	
	public MyFilter(EventNotify eventNotify) {
		this.eventNotify = eventNotify;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("필터 실행 됨!!!!!");
		
		HttpServletResponse serveletResponse = (HttpServletResponse) response;
		serveletResponse.setContentType("text/event-stream; charset=utf-8");
		PrintWriter out = serveletResponse.getWriter();
		
		for (int i=0; i<5; i++) {
			out.print("응답:"+i+"\n");
			out.flush();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while(true) {
			try {
				if(eventNotify.getChagne()) {
					int lastIndext = eventNotify.getEvents().size()-1;
					out.print("응답:"+eventNotify.getEvents().get(lastIndext)+"\n");
					out.flush();
					eventNotify.setChange(false);
				}
				
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


