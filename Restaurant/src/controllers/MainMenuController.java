package controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



import interfaces.IMainMenuController;
import models.Client;
import models.Order;
import modelsrepository.RepositoryClients;
import modelsrepository.RepositoryOrders;
import utilities.RepositoryUtils;
import views.MenuViews;

public class MainMenuController implements IMainMenuController {
	
	RepositoryOrders orders = RepositoryOrders.getInstance();
	MenuViews MV = new MenuViews();
	
	@Override
	public boolean newOrder(Client c, LocalDateTime id) {
		boolean result=false;
		Order tmp = MV.addOrderView(c, id);
		orders.getAllOrders().add(tmp);
		result=true;
		return result;
	}
    @Override
    public boolean changeOrder(Client c) {
        boolean result=false;
        if (c!=null) {
        	
        	for(int i=0;i<orders.getAllOrders().size();i++) {
        		
        		if(orders.getAllOrders().get(i).equals(c)) {
        			
        		orders.getAllOrders().get(i).setClient(c);
        		result =true;
        		}
        	}
        }
        return result;
    }

    @Override
    public boolean changeOrder(LocalDateTime d) {
        boolean result=false;
        
        if (d!=null) {
        	
        	for(int i=0;i<orders.getAllOrders().size();i++) {
        		
        		if(orders.getAllOrders().get(i).equals(d)) {
        			
        		orders.getAllOrders().get(i).setDate(d);
        		result =true;
        		}
        	}
        }
        return result;
    }

    @Override
    public boolean changeOrder(Client c, LocalDateTime d) {
        boolean result=false;
        boolean resultC=false;
        boolean resultD=false;
        if(c!=null && d!= null) {
        	//CLIENT
        	for(int i=0;i<orders.getAllOrders().size();i++) {
        		
        		if(orders.getAllOrders().get(i).equals(c)) {
        			
        		orders.getAllOrders().get(i).setClient(c);
        		resultC =true;
        		}
        	//DATE
        	for(int j=0;j<orders.getAllOrders().size();j++) {
        		
        		if(orders.getAllOrders().get(j).equals(d)) {
        			
        		orders.getAllOrders().get(j).setDate(d);
        		resultD =true;
        		
        	
        		}
        	}
        	
        	if(resultC ==true && resultD ==true) {
        	
        		result=true;
        	}
        	}
        }
        return result;
    }

    @Override
    public boolean deleteOrder(Client c) {
        boolean result=false;
        if(c!= null) {
        for(int i=0; i<orders.getAllOrders().size();i++) {
        	
        	if(orders.getAllOrders().get(i).equals(c)) {
        		
        		orders.getAllOrders().remove(i);
        		result=true;
        	}
        }
        }
        return result;
    }

    @Override
    public boolean deleteOrder(LocalDateTime d) {
        boolean result=false;
        if(d!= null) {
        for(int i=0; i<orders.getAllOrders().size();i++) {
        	
        	if(orders.getAllOrders().get(i).equals(d)) {
        		
        		orders.getAllOrders().remove(i);
        		result=true;
        	}
        }
        }
        return result;
    }

    @Override
    public boolean deleteOrder(Client c, LocalDateTime d) {
        boolean result=false;
        boolean resultC=false;
        boolean resultD=false;
        if (c != null && d !=null) {
        
        	//CLIENT
        	for(int i=0;i<orders.getAllOrders().size();i++) {
        		
        		if(orders.getAllOrders().get(i).equals(c)) {
        			
        		orders.getAllOrders().remove(i);
        		resultC =true;
        		}
        	//DATE
        	for(int j=0;j<orders.getAllOrders().size();j++) {
        		
        		if(orders.getAllOrders().get(j).equals(d)) {
        			
        		orders.getAllOrders().remove(j);
        		resultD =true;
        		
        	
        		}
        		if(resultC ==true && resultD ==true) {
                	
            		result=true;
            	}
        	
        	
        }
        	}
        }
        return result;
    }

    @Override
    public double cashToDay() {
        double result=0;
        int day=Calendar.DAY_OF_WEEK_IN_MONTH;
        
        for(int i=0;i<orders.getAllOrders().size();i++) {
        	
        	int day1= orders.getAllOrders().get(i).getDate().getDayOfMonth();
        	
        	boolean isPayed = orders.getAllOrders().get(i).isPayed();
        	
        	if(day == day1 && isPayed) {
        		
        		result+= orders.getAllOrders().get(i).getTotal();
        	}
        }
        return result;
    }

    @Override
    public double cashThisMonth() {
    	double result=0;
        int moth=Calendar.MONTH + 1;
        
        for(int i=0;i<orders.getAllOrders().size();i++) {
        	
        	int moth1= orders.getAllOrders().get(i).getDate().getMonthValue();
        	
        	boolean isPayed = orders.getAllOrders().get(i).isPayed();
        	
        	if(moth == moth1 && isPayed) {
        		
        		result+= orders.getAllOrders().get(i).getTotal();
        	}
        }
        return result;
    }

    @Override
    public double cashTotal() {
        double result=0;
        for(int i=0;i<orders.getAllOrders().size();i++) {
        	
        	result = result + orders.getAllOrders().get(i).getTotal(); 
        }
        return result;
    }

    @Override
    public ArrayList<Order> viewOrdersNotPayed() {
        ArrayList<Order> result= new ArrayList<Order>();
        for(int i=0;i<orders.getAllOrders().size();i++) {
        	boolean isPayed= orders.getAllOrders().get(i).isPayed();
		        if(!isPayed) {
		        	result.add(orders.getAllOrders().get(i));
		        }
        }
        return result;
    }

    @Override
    public ArrayList<Order> viewOrdersPendingDelivered() {
        ArrayList<Order> result=new ArrayList<Order>();
        for(int i=0;i<orders.getAllOrders().size();i++) {
        	
        	boolean delivered= orders.getAllOrders().get(i).isDelivered();
        	
        	if(!delivered) {
        		
        		result.add(orders.getAllOrders().get(i));
        	}
        }
        
        return result;
    }

    @Override
    public void saveAllAndClose() {
    	
    	RepositoryClients RC = RepositoryClients.getInstance();
        RepositoryOrders RO = RepositoryOrders.getInstance();
        RepositoryUtils tmp = new RepositoryUtils();
        
        tmp.saveClients("clients.data", RC);
        tmp.saveOrders("orders.data", RO);
        
    }
    
    

}