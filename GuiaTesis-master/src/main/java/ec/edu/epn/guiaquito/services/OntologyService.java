package ec.edu.epn.guiaquito.services;

import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;

import ec.edu.epn.guiaquito.dao.InterestTypeDAO;
import ec.edu.epn.guiaquito.dao.UserDAO;
import ec.edu.epn.guiaquito.dao.UserInterestTypeDAO;
import ec.edu.epn.guiaquito.entities.InterestType;
import ec.edu.epn.guiaquito.entities.User;
import ec.edu.epn.guiaquito.entities.UserInterestType;


@Stateless
public class OntologyService {

	@EJB
	private UserDAO userDAO;

	@EJB
	private InterestTypeDAO interestTypeDAO;

	@EJB
	private UserInterestTypeDAO userInterestTypeDAO;
	
	
	//@Override	
	public User createUserOntolgy(User user) throws Exception {
		User userSet = new User();
		try {
			
			String consultaCrear =  
				"PREFIX OntologyInterest: <http://www.owl-ontologies.com/OntologyIU-lite.owl#>\n"
				+"prefix owl: <http://www.w3.org/2002/07/owl#>\n"
				+"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+"prefix swrl: <http://www.w3.org/2003/11/swrl#>\n"
				+"prefix swrlb: <http://www.w3.org/2003/11/swrlb#>\n"
				+"prefix xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+"prefix xsp: <http://www.owl-ontologies.com/2005/08/07/xsp.owl#>"
	            + "INSERT DATA"	         
	            + "{" 
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#User_"+user.getFirstName()+">   a  OntologyInterest:User;"
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#has>    OntologyInterest:UserInterestType_"+user.getFirstName()+";"
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#login>   OntologyInterest:Profile_"+user.getFirstName()+";"
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#starts>   OntologyInterest:Session_"+user.getFirstName()+"."
	            
	            +" <http://www.owl-ontologies.com/OntologyIU-lite.owl#Profile_"+user.getFirstName()+"> a  OntologyInterest:Profile;"
	            + "   OntologyInterest:lastName    \""+user.getLastName()+"\" ;"
	            + "   OntologyInterest:firstName    \""+user.getFirstName()+"\" ;"
	            + "   OntologyInterest:birthay    \""+user.getBirthday()+"\" ;"
	            + "   OntologyInterest:email    \""+user.getEmail()+"\" ;"
	            + "   OntologyInterest:facebookId   \""+user.getFacebookId()+"\";" 
	           // + "   OntologyInterest:id   \""+user.getId()+"\";" 
	            + "  <http://www.owl-ontologies.com/OntologyIU-lite.owl#logged_in_by>     OntologyInterest:User_"+user.getFirstName()+"."
	            
	            
	            	    + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#UserInterestType_"+user.getFirstName()+"> a  OntologyInterest:UserInterestType;"
	     	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#belongs_to>     OntologyInterest:User_"+user.getFirstName()+";"
	     	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#prioritizes>    OntologyInterest:InterestType_"+user.getFirstName()+"."
	            + "}   ";
			
			System.out.println(consultaCrear);
			String id = UUID.randomUUID().toString();
			System.out.println(String.format("Adding %s", id));
			UpdateProcessor upp = UpdateExecutionFactory.createRemote(
					UpdateFactory.create(String.format(consultaCrear, id)),
					"http://138.197.121.73:8080/OntologiaIU-lite/update");
			upp.execute();
			
			
			userSet = user;
		} catch (Exception e) {

		}

		// TODO Auto-generated method stub
		System.out.println(userSet);
		return userSet;
	}
	
	

		public InterestType updateInterestOntology(String interesttype, User user) throws Exception {
			InterestType interesttypeSet = new InterestType();
			//User userSet = new User();
			try {
				
				String consultaCrear =  
					"PREFIX OntologyInterest: <http://www.owl-ontologies.com/OntologyIU-lite.owl#>\n"
					+"prefix owl: <http://www.w3.org/2002/07/owl#>\n"
					+"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
					+"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
					+"prefix swrl: <http://www.w3.org/2003/11/swrl#>\n"
					+"prefix swrlb: <http://www.w3.org/2003/11/swrlb#>\n"
					+"prefix xsd: <http://www.w3.org/2001/XMLSchema#>\n"
					+"prefix xsp: <http://www.owl-ontologies.com/2005/08/07/xsp.owl#>"
		            + "INSERT DATA"	         
		            + "{" 
		                       
		            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#InterestType_"+user.getFirstName()+"> a OntologyInterest:InterestType;"
		            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#prioritized_by_a>     OntologyInterest:UserInterestType_"+user.getFirstName()+";"
		            + "  OntologyInterest:church    \""+interesttype+"\" ."
		            + "}   ";
				
				System.out.println(consultaCrear);
				String id = UUID.randomUUID().toString();
				System.out.println(String.format("Adding %s", id));
				UpdateProcessor upp = UpdateExecutionFactory.createRemote(
						UpdateFactory.create(String.format(consultaCrear, id)),
						"http://138.197.121.73:8080/OntologiaIU-lite/update");
				upp.execute();
				
				
				//interesttypeSet = interesttype;
			} catch (Exception e) {

			}

			// TODO Auto-generated method stub
			return interesttypeSet;
		
		
			}

	
	
}
