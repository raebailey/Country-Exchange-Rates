package models;

public class Page {
	private static Page page;
	private final PageFactory pagefactory;
	
	private Page() {
		this.pagefactory = new PageFactory();
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
	
	

}
