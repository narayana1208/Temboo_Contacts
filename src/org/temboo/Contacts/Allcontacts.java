package org.temboo.Contacts;

import com.temboo.Library.Google.Contacts.GetAllContacts;
import com.temboo.Library.Google.Contacts.GetAllContacts.GetAllContactsInputSet;
import com.temboo.Library.Google.Contacts.GetAllContacts.GetAllContactsResultSet;
import com.temboo.Library.Google.OAuth.FinalizeOAuth;
import com.temboo.Library.Google.OAuth.FinalizeOAuth.FinalizeOAuthInputSet;
import com.temboo.Library.Google.OAuth.FinalizeOAuth.FinalizeOAuthResultSet;
import com.temboo.Library.Google.OAuth.InitializeOAuth;
import com.temboo.Library.Google.OAuth.InitializeOAuth.InitializeOAuthInputSet;
import com.temboo.Library.Google.OAuth.InitializeOAuth.InitializeOAuthResultSet;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

/**import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;**/

public class Allcontacts 
{
	private static final String  ClientId="354986878601-aaglejlchinucghafapsjim1okh90lm8.apps.googleusercontent.com";
	private static  final String Secretkey="CE0FWSDOrc3ABxJbpRe0TXiv";
	
	public String AuthorizationURL="";
	public String CallBackURL="";
	
	public String AccessToken="";
	public String RefreshToken="";
	public String seconds;
	
  public static void main(String[] args)throws Exception
  {
	  Allcontacts getcontacts=new Allcontacts();
	  System.out.println("client id "+Allcontacts.ClientId);
	  System.out.println("client id "+Allcontacts.Secretkey);
	  
	  TembooSession session = new TembooSession("narayana1208", "myFirstApp", "2fd978fdb07b4f3c92dd73d098a995ff");
	    InitializeOAuthResultSet initializeOAuthResults=Allcontacts.initializeOauth(session);
		String authorizationURL=initializeOAuthResults.get_AuthorizationURL();
		String callBackURL=initializeOAuthResults.get_CallbackID();
		getcontacts.setAuthorizationURL(authorizationURL);
		getcontacts.setCallBackURL(callBackURL);
		
		System.out.println("get contacts authorization URL "+getcontacts.AuthorizationURL);
		System.out.println("get contacts call backURL :- "+getcontacts.CallBackURL);
		
		FinalizeOAuthResultSet finalizeOAuthResults=Allcontacts.finalizeOauth(session,getcontacts);
		
		getcontacts.AccessToken=finalizeOAuthResults.get_AccessToken();
		getcontacts.RefreshToken=finalizeOAuthResults.get_RefreshToken();
		getcontacts.seconds=finalizeOAuthResults.get_Expires();
		
		
		System.out.println("get contacts Acess Token "+getcontacts.AccessToken);
		System.out.println("get contacts Refresh Token:- "+getcontacts.RefreshToken);
		
		
		Allcontacts.GetAllContacts(session,getcontacts);
		
		
		
		
	  
  }
  public static InitializeOAuthResultSet initializeOauth(TembooSession session)throws Exception
  {
	// Instantiate the Choreo, using a previously instantiated TembooSession object, eg:
	

	InitializeOAuth initializeOAuthChoreo = new InitializeOAuth(session);

	// Get an InputSet object for the choreo
	InitializeOAuthInputSet initializeOAuthInputs = initializeOAuthChoreo.newInputSet();

	// Set inputs
	initializeOAuthInputs.set_ClientID("354986878601-aaglejlchinucghafapsjim1okh90lm8.apps.googleusercontent.com");
	initializeOAuthInputs.set_Scope("https://www.googleapis.com/auth/contacts.readonly");

	// Execute Choreo
	InitializeOAuthResultSet initializeOAuthResults = initializeOAuthChoreo.execute(initializeOAuthInputs);

	//Allcontacts.setAuthorizationURL(authorizationURL);
	//Allcontacts.setCallBackURL(callBackURL);
	System.out.println("Authorization  URL :- "+initializeOAuthResults.get_AuthorizationURL());
	System.out.println("Call Back URL :-"+ initializeOAuthResults.get_CallbackID());
	
	Desktop d=Desktop.getDesktop();
	d.browse(new URI(initializeOAuthResults.get_AuthorizationURL()));
	
	Runtime rt=Runtime.getRuntime();
	rt.exec("rundll32 url.dll,FileProtocolHandler "+initializeOAuthResults.get_AuthorizationURL());
	
	return initializeOAuthResults;
  }
  
  public static FinalizeOAuthResultSet finalizeOauth(TembooSession session,Allcontacts get)throws Exception
  {
	// Instantiate the Choreo, using a previously instantiated TembooSession object, eg:
	// TembooSession session = new TembooSession("narayana1208", "myFirstApp", "2fd978fdb07b4f3c92dd73d098a995ff");

	FinalizeOAuth finalizeOAuthChoreo = new FinalizeOAuth(session);

	// Get an InputSet object for the choreo
	FinalizeOAuthInputSet finalizeOAuthInputs = finalizeOAuthChoreo.newInputSet();

	// Set inputs
	//Call_back URL :-"narayana1208/3a94a907-c776-4d43-b936-f404956191b2"
	//Client Secret :-"CE0FWSDOrc3ABxJbpRe0TXiv"
	finalizeOAuthInputs.set_CallbackID(get.CallBackURL);
	finalizeOAuthInputs.set_ClientSecret(Allcontacts.Secretkey);
	//"354986878601-aaglejlchinucghafapsjim1okh90lm8.apps.googleusercontent.com"
	finalizeOAuthInputs.set_ClientID(Allcontacts.ClientId);

	System.out.println("Finalize AUTH session");
	// Execute Choreo
	FinalizeOAuthResultSet finalizeOAuthResults = finalizeOAuthChoreo.execute(finalizeOAuthInputs);
	
	return finalizeOAuthResults;
	  
  }
  
  public static void GetAllContacts(TembooSession session,Allcontacts get) throws TembooException, Exception, IOException 
  {
	// Instantiate the Choreo, using a previously instantiated TembooSession object, eg:
	// TembooSession session = new TembooSession("narayana1208", "myFirstApp", "2fd978fdb07b4f3c92dd73d098a995ff");

	GetAllContacts getAllContactsChoreo = new GetAllContacts(session);

	// Get an InputSet object for the choreo
	GetAllContactsInputSet getAllContactsInputs = getAllContactsChoreo.newInputSet();

	// Set inputs
	// Set inputs
	//"CE0FWSDOrc3ABxJbpRe0TXiv"
	getAllContactsInputs.set_ClientSecret(Allcontacts.Secretkey);
	//"1/WpuCpjJN2fKBk38eXtFsdmB7oc_CQiWwodSa3AIYiBIMEudVrK5jSpoR30zcRFq6"
	getAllContactsInputs.set_RefreshToken(get.RefreshToken);
	//"354986878601-aaglejlchinucghafapsjim1okh90lm8.apps.googleusercontent.com"
	getAllContactsInputs.set_ClientID(Allcontacts.ClientId);

	// Execute Choreo
	GetAllContactsResultSet getAllContactsResults = getAllContactsChoreo.execute(getAllContactsInputs);
	
	
	System.out.println("\n THE RESULT OF GOOGLE GET ALL CONTACTS \n");
	
	System.out.println("\n ACCESS TOKEN \n"+getAllContactsResults.get_AccessToken());
	
	System.out.println("The response of Get all Contacts\n"+getAllContactsResults.get_Response());
	
	
	/***DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = factory.newDocumentBuilder();

    // create a new document from input stream and an empty systemId
    Document doc = builder.parse(getAllContactsResults.get_Response());

    // get the first element
    Element element = doc.getDocumentElement();

    // get all child nodes
    NodeList nodes = element.getChildNodes();

    // print the text content of each child
    for (int i = 0; i < nodes.getLength(); i++) {
       System.out.println("" + nodes.item(i).getTextContent());
    }**/
	
	
	
	
	
  }
  
  public  void setAuthorizationURL(String auth)
  {
	  AuthorizationURL=auth;
  }
  public  void setCallBackURL(String callback)
  {
	  CallBackURL=callback;
  }
  
}
