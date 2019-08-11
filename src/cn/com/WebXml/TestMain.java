package cn.com.WebXml;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class TestMain {
	public static void main(String[] args) {
		
		WeatherWebService weatherWebService = new WeatherWebServiceLocator();
        WeatherWebServiceSoap soap = null;
		try {
			soap = weatherWebService.getWeatherWebServiceSoap();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("--------weatherInfo--------");
       String[] arr = null;
	try {
		arr = soap.getWeatherbyCityName("重庆");
	} catch (RemoteException e) {
		e.printStackTrace();
	}
        for(String str : arr) {
            System.out.println("weatherInfo: " + str);
        }
		
	}
}
