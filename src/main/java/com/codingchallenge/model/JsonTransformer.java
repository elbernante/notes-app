package com.codingchallenge.model;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

	private Gson gson = new Gson();
	
	@Override
	public String render(Object arg0) throws Exception {
		return gson.toJson(arg0);
	}

}
