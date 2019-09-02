package Constant;

import RequestClasses.Response;

public enum Request {

	LOGIN("0"),
	RESPONSE("1"),
	USERID("2"),
	REGISTER("3");

	Request(String s){
		s.toString();
	}
}
