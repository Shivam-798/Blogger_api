package com.blog.config;

public class AppContest {
	
	public static final String Page_NUMBER="0";
	public static final String PAGE_SIZE="5";
	public static final String SORT_BY="postId";
	public static final String SORT_DIR="asc";
	public static final Integer NORMAL_USER=502;
	public static final Integer ADMIN_ROLE= 501;
	
}
//jab bhi koi contest value use karte hai ham apne project mein
//jase getAllPost wali controller mein use kiya hai
//in constant ko directly apne code mein nahi likhna chahiye
//in constant ke liye ham apne code mein constant ki class bana lenge aur wahi per
//in constant ko rakh denge taki agar kahi change karna pade to ham ke jagah change karke 
//pure code mein change kar sake
