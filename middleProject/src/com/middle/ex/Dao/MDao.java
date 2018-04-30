package com.middle.ex.Dao;

public class MDao {
	private MDao() {}
	
	private static class holder{
		private static final MDao instance = new MDao();
	}
	
	public static MDao getInstance() {
		return holder.instance;
	}

	
}
