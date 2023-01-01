package models;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Page {
	private static Page page;
	private final PageFactory pagefactory;
	private static ExecutorService fixedPool;
	
	private Page() {
		this.pagefactory = new PageFactory();
		fixedPool = Executors.newFixedThreadPool(2);
	}
	
	public static synchronized Page getInstance() {
		if(page==null) {
			page = new Page();
		}
		return page;
	}

	public PageFactory getPagefactory() {
		return pagefactory;
	}
	
	public static ExecutorService getExecutor() {
		if(fixedPool==null) {
			fixedPool = Executors.newFixedThreadPool(2);
		}
		return fixedPool;
	}
	
	

}
