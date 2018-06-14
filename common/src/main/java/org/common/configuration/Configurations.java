package org.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


public class Configurations {
	

	public static final String rabbitUsername = "admin";
	public static final String rabbitPassword = "admin";
	public static final String rabbitHost = "localhost";
	public static final String rabbitQueueMicroserviceToManagement = "microservice-management";
	public static final String rabbitQueueManagementToMicroservice = "management-microservice";

	
	
}
