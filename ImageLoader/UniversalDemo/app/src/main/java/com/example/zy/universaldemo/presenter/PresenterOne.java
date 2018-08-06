package com.example.zy.universaldemo.presenter;

public class PresenterOne<ViewOne> {
	ViewOne viewOne = null;
	
	public PresenterOne() {
	
	}
	
	public void attachView(ViewOne viewOne){
		this.viewOne = viewOne;
	}
	
	public boolean isActive(){
		return this.viewOne != null;
	}
	//public void
}
