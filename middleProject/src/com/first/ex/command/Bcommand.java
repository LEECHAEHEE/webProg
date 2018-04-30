package com.first.ex.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Bcommand {
	void execute(HttpServletRequest request, HttpServletResponse response);
}
