package myPack;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
public class Lista {
       //String url="jdbc:sqlserver://10.33.200.43:51433;database=RemoteApp;user=iVector;password=!Vector$2020;";
       String url="jdbc:sqlserver://10.33.193.93:51433;database=RemoteApp_Phase2;user=RemoteApp;password=wns@1234;"; 
	String[] hostname = new String[50];
        String[] uid = new String[50];
        String[] employee = new String[50];
        String[] country = new String[50];
	String[] eventname = new String[50];
	String[] date=new String[50];
	String[] clientip = new String[50];
	String[] logs = new String[50];
	String[] serialnumber = new String[50];
        String[] name =new String[50];
        String[] departamentID =new String[50];
        String[] departament =new String[50];
        String[] location =new String[50];
        String[] NetworkInfo=new String[50];
        String[] DLSpeed=new String[50];
        String[] UPSpeed=new String[50];
        Integer[] Ping=new Integer[50];
        String[] Jitter=new String[50];
        Double [] PolandBandwidth=new Double[5];
        String[] wanip = new String[50];
        String[] isp = new String[50];
        String[] login=new String[50];
        String[] itIsRunned=new String[50];
        int countInstalls=0;
        int attempts;
        int count = 0;
        
        SimpleDateFormat parser =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        
        
        
        public ArrayList<ArrayList> exportInstalls() throws Exception{
		
            try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}catch(ClassNotFoundException e){}
		ArrayList<String> hostname1 = new ArrayList<String>();
                ArrayList<String> uid1 = new ArrayList<String>();
                ArrayList<String> employee1 = new ArrayList<String>();
                ArrayList<String> country1 = new ArrayList<String>();
                ArrayList<String> location1 = new ArrayList<String>();
                ArrayList<String> NetworkInfo1 = new ArrayList<String>();
                ArrayList<String> date1 = new ArrayList<String>();
                ArrayList<Integer> counttotal = new ArrayList<Integer>();
                Connection con = DriverManager.getConnection(url);
                Statement st = con.createStatement();
		String query = "SELECT  * FROM (SELECT Hostname,UserID,UserName,Country,Location,NetworkInfo,ispName,DateTime, ROW_NUMBER() OVER (PARTITION BY UserID ORDER BY UserID) AS RowNumber FROM   bandwidth_dup ) AS a WHERE   a.RowNumber = 1";
                ResultSet rs = st.executeQuery(query);        
                while(rs.next()){
                        String host=rs.getString("Hostname");
                        hostname1.add(host);
                        uid1.add(rs.getString("UserID"));
                        employee1.add(rs.getString("UserName"));
                        country1.add(rs.getString("Country"));
                        location1.add(rs.getString("Location"));
                        NetworkInfo1.add(rs.getString("NetworkInfo"));
                        Date dataParsata = parser.parse(rs.getString("DateTime"));
                        String dat = formatter.format(dataParsata);
                        date1.add(dat);
                        count++;
                        
                 }
                con.close();
                counttotal.add(count);
                ArrayList<ArrayList> total=new ArrayList<ArrayList>();
                total.add(hostname1);total.add(uid1);total.add(employee1);total.add(country1);total.add(location1);total.add(NetworkInfo1);total.add(date1);total.add(counttotal);
                return total;
                
       }
        
        
        public void listInstalls(String host,String userid,String getcountry,String getlocation,int offset,String startdate,String enddatefaratimp) throws Exception{
	CallableStatement callableStatement = null;
            try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}catch(ClassNotFoundException e){}
		
                String enddate;
                if("".equals(enddatefaratimp)){
                    enddate="01/01/2050";
                }
                else{enddate=enddatefaratimp+" 23:59:59";
                        
                        }
               Connection con = DriverManager.getConnection(url);String getData = "{call getBandwidth_dup(?,?,?,?,?,?,?)}";
                callableStatement = con.prepareCall(getData);
                callableStatement.setString(1, startdate);
                callableStatement.setString(2, enddate);
                callableStatement.setString(3, host+"%");
                callableStatement.setString(4, userid+"%");
                callableStatement.setString(5, getcountry+"%");
                callableStatement.setString(6, getlocation+"%");
                callableStatement.setInt(7, offset);
                ResultSet rs= callableStatement.executeQuery();
		while(rs.next()& count<30){
			hostname[count]=rs.getString("Hostname");
                        uid[count] = rs.getString("UserID");
                        country[count]= rs.getString("Country");
                        location[count] = rs.getString("Location");
                        employee[count] = rs.getString("UserName");
                        NetworkInfo[count] = rs.getString("NetworkInfo");
                        isp[count]=rs.getString("ispName");
                        Date dataParsata = parser.parse(rs.getString("DateTime"));
                        date[count] = formatter.format(dataParsata);
                        //itIsRunned[count]=testIfRun(hostname[count]);
                        count++;
                        
		}con.close();
       }
	
  
	public void listBandwidth(String host,String userid,String getcountry,String getlocation,int offset,String startdate,String enddatefaratimp) throws Exception{
		CallableStatement callableStatement = null;
            try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}catch(ClassNotFoundException e){}
		
                String enddate;
                if("".equals(enddatefaratimp)){
                    enddate="01/01/2050";
                }
                else{enddate=enddatefaratimp+" 23:59:59";
                        
                        }
               Connection con = DriverManager.getConnection(url);String getData = "{call getBandwidth(?,?,?,?,?,?,?)}";
                callableStatement = con.prepareCall(getData);
                callableStatement.setString(1, startdate);
                callableStatement.setString(2, enddate);
                callableStatement.setString(3, host+"%");
                callableStatement.setString(4, userid+"%");
                callableStatement.setString(5, getcountry+"%");
                callableStatement.setString(6, getlocation+"%");
                callableStatement.setInt(7, offset);
                ResultSet rs= callableStatement.executeQuery();
		
                        while(rs.next()& count<30){
			hostname[count]=rs.getString("Hostname");
                        uid[count] = rs.getString("UserID");
                        country[count]= rs.getString("Country");
                        location[count] = rs.getString("Location");
                        employee[count] = rs.getString("Username");
                        NetworkInfo[count] = rs.getString("IP_Address");
                        DLSpeed[count] = rs.getString("DL_Speed_MBPS");
                        UPSpeed[count] = rs.getString("UP_Speed_MBPS");
                        Ping[count] = rs.getInt("Ping_MS");
                        Jitter[count] = rs.getString("Jitter_MS");
                        wanip[count]=rs.getString("WAN_IP");
                        isp[count]=rs.getString("ISP_Name");
                        Date dataParsata = parser.parse(rs.getString("DateTime"));
                        date[count] = formatter.format(dataParsata);
                        count++;
                        
		}
                
                
	}
	
        public void listFiltered(String host,String userid,String getCountry,String event,int offset,String startdate,String enddatefaratimp)throws Exception{
	    
            CallableStatement callableStatement = null;	
            try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}catch(ClassNotFoundException e){}
                String enddate;
                if("".equals(enddatefaratimp)){
                    enddate="01/01/2050";
                }
                else{enddate=enddatefaratimp+" 23:59:59";}
                
                Connection con = DriverManager.getConnection(url);
		String getEvents = "{call getFilteredEvents(?,?,?,?,?,?,?)}";
                callableStatement = con.prepareCall(getEvents);
                callableStatement.setString(1, startdate);
                callableStatement.setString(2, enddate);
                callableStatement.setString(3, host+"%");
                callableStatement.setString(4, getCountry+"%");
                callableStatement.setString(5,event+"%");
                callableStatement.setInt(6,offset);
                callableStatement.setString(7,userid+"%");
                ResultSet rs= callableStatement.executeQuery();
                while(rs.next()& count<30){
			hostname[count]=rs.getString("Hostname");
                        uid[count] = rs.getString("uid");
                        eventname[count] = rs.getString("eventname");
                        clientip[count] = rs.getString("clientip");
                        logs[count] = rs.getString("logs");
                        serialnumber[count] = rs.getString("serialnumber");
                        Date dataParsata = parser.parse(rs.getString("eventdate"));
                        date[count] = formatter.format(dataParsata);
                        country[count]= rs.getString("Country");
                        location[count] = rs.getString("Location");
                        employee[count] = rs.getString("UserName");
                        count++;
		}
                
                
	}
        
        public List<Integer> listChart(String host,String userid,String event,String getcountry,String startdate,String enddatefaratimp) throws Exception{
	    CallableStatement callableStatement = null;	
            try{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}catch(ClassNotFoundException e){}
		
                String enddate;
                if("".equals(enddatefaratimp)){
                    enddate="01/01/2050";
                }
                else{enddate=enddatefaratimp+" 23:59:59";
                        
                        }
                Connection con = DriverManager.getConnection(url);
		String getData = "{call eventsChart(?,?,?,?,?)}";
                callableStatement = con.prepareCall(getData);
                callableStatement.setString(1, startdate);
                callableStatement.setString(2, enddate);
                callableStatement.setString(3, host+"%");
                callableStatement.setString(4, getcountry+"%");
                callableStatement.setString(5,userid+"%");
		
                List<Integer> counturi=new ArrayList<Integer>();
                int countEvents=0; //countEvents
                int b=0; //networkresetcount
                int c=0; //proxy
                int d=0;  //cache
                int e=0; //gpupdate
                int f=0; //network logs
                int g=0;
                int h=0;
                int i=0;
                int j=0;    
                int k=0;
                int l=0; 
                int m=0; 
                ResultSet rs= callableStatement.executeQuery();
		
		while(rs.next()){
                    
		String	eventtype = rs.getString("eventname");
                   if (eventtype.equals("Restart Network")){b++;}
                   if (eventtype.equals("Check Proxy Settings")){c++;}
                   if (eventtype.equals("Clear Internet Cache")){d++;}
                   if (eventtype.equals("GP-Update")){e++;}
                   if (eventtype.equals("Collect Network Logs")){f++;}
                   if (eventtype.equals("Clear Network Cache")){g++;}
                   if (eventtype.equals("Force McAfee Update")){h++;}
                   if (eventtype.equals("Install-VPN")){i++;}
                   if (eventtype.equals("Update SCCM")){j++;}
                   if (eventtype.equals("Enable Audio Services")){k++;}
                   if (eventtype.equals("Diagnostic Check")){l++;}
                   if (eventtype.equals("Re-build Profile")){m++;}
                   
                   
                   countEvents++; }
                
                   counturi.add(countEvents);counturi.add(b);counturi.add(c);counturi.add(d);counturi.add(e);
                   counturi.add(f);counturi.add(g);counturi.add(h);counturi.add(i);counturi.add(j);counturi.add(k);counturi.add(l);counturi.add(m);
                   return counturi;
	}
        
        public List<List> listBandwidthChart(String host,String userid,String startdate,String enddatefaratimp) throws Exception{
	CallableStatement callableStatement = null;		
            try{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}catch(ClassNotFoundException e){}
		String enddate;
                if("".equals(enddatefaratimp)){
                    enddate="01/01/2030";
                }
                else{enddate=enddatefaratimp+" 23:59:59";
                        
                        }
                
                Connection con = DriverManager.getConnection(url);
		
                double ChinaDL=0;double ChinaUP=0;double ChinaPG=0;double ChinaJT=0;int ChinaCount=0;
                double IndiaDL=0;double IndiaUP=0;double IndiaPG=0;double IndiaJT=0;int IndiaCount=0;
                double PhilippinesDL=0;double PhilippinesUP=0;double PhilippinesPG=0;double PhilippinesJT=0;int PhilippinesCount=0;
                double PolandDL=0;double PolandUP=0;double PolandPG=0;double PolandJT=0;int PolandCount=0;
                double RomaniaDL=0;double RomaniaUP=0;double RomaniaPG=0;double RomaniaJT=0;int RomaniaCount=0;
                double SouthADL=0;double SouthAUP=0;double SouthAPG=0;double SouthAJT=0;int SouthACount=0;
                double SriLankaDL=0;double SriLankaUP=0;double SriLankaPG=0;double SriLankaJT=0;int SriLankaCount=0;
                double TurkeyDL=0;double TurkeyUP=0;double TurkeyPG=0;double TurkeyJT=0;int TurkeyCount=0;
                double UKDL=0;double UKUP=0;double UKPG=0;double UKJT=0;int UKCount=0;   
                double USDL=0;double USUP=0;double USPG=0;double USJT=0;int USCount=0;
                double CostaRicaDL=0;double CostaRicaUP=0;double CostaRicaPG=0;double CostaRicaJT=0;int CostaRicaCount=0;
                double SpainDL=0;double SpainUP=0;double SpainPG=0;double SpainJT=0;int SpainCount=0;        
		String getData = "{call getBandwidthChart(?,?,?,?)}";
                callableStatement = con.prepareCall(getData);
                callableStatement.setString(1, startdate);
                callableStatement.setString(2, enddate);
                callableStatement.setString(3, host+"%");
                callableStatement.setString(4, userid+"%");
                ResultSet rs= callableStatement.executeQuery();
                
		while(rs.next()){
                String dlspeed = rs.getString("DLSpeed");
                String upspeed = rs.getString("UPSpeed");
                String aping = rs.getString("Ping");
                String ajitter = rs.getString("Jitter");
                String BDcountry = rs.getString("Country");
                if (BDcountry == null){BDcountry="Unknown";}
                
                
                   if ("China".equals(BDcountry)){ChinaDL=(ChinaDL+Double.parseDouble(dlspeed));ChinaUP=(ChinaUP+Double.parseDouble(upspeed));ChinaPG=(ChinaPG+Double.parseDouble(aping));ChinaJT=(ChinaJT+Double.parseDouble(ajitter));ChinaCount++;}
                   if ("India".equals(BDcountry)){IndiaDL=(IndiaDL+Double.parseDouble(dlspeed));IndiaUP=(IndiaUP+Double.parseDouble(upspeed));IndiaPG=(IndiaPG+Double.parseDouble(aping));IndiaJT=(IndiaJT+Double.parseDouble(ajitter));IndiaCount++;}
                   if ("Philippines".equals(BDcountry)){PhilippinesDL=(PhilippinesDL+Double.parseDouble(dlspeed));PhilippinesUP=(PhilippinesUP+Double.parseDouble(upspeed));PhilippinesPG=(PhilippinesPG+Double.parseDouble(aping));PhilippinesJT=(PhilippinesJT+Double.parseDouble(ajitter));PhilippinesCount++;}
                   if ("Poland".equals(BDcountry)){PolandDL=(Double.parseDouble(dlspeed)+PolandDL);PolandUP=(PolandUP+Double.parseDouble(upspeed));PolandPG=(PolandPG+Double.parseDouble(aping));PolandJT=(PolandJT+Double.parseDouble(ajitter));PolandCount++;}
                   if ("Romania".equals(BDcountry)){RomaniaDL=(RomaniaDL+Double.parseDouble(dlspeed));RomaniaUP=(RomaniaUP+Double.parseDouble(upspeed));RomaniaPG=(RomaniaPG+Double.parseDouble(aping));RomaniaJT=(RomaniaJT+Double.parseDouble(ajitter));RomaniaCount++;}
                   if ("South Africa".equals(BDcountry)){SouthADL=(SouthADL+Double.parseDouble(dlspeed));SouthAUP=(SouthAUP+Double.parseDouble(upspeed));SouthAPG=(SouthAPG+Double.parseDouble(aping));SouthAJT=(SouthAJT+Double.parseDouble(ajitter));SouthACount++;}
                   if ("Sri Lanka".equals(BDcountry)){SriLankaDL=(SriLankaDL+Double.parseDouble(dlspeed));SriLankaUP=(SriLankaUP+Double.parseDouble(upspeed));SriLankaPG=(SriLankaPG+Double.parseDouble(aping));SriLankaJT=(SriLankaJT+Double.parseDouble(ajitter));SriLankaCount++;}
                   if ("Turkey".equals(BDcountry)){TurkeyDL=(TurkeyDL+Double.parseDouble(dlspeed));TurkeyUP=(TurkeyUP+Double.parseDouble(upspeed));TurkeyPG=(TurkeyPG+Double.parseDouble(aping));TurkeyJT=(TurkeyJT+Double.parseDouble(ajitter));TurkeyCount++;}
                   if ("UK".equals(BDcountry)){UKDL=(UKDL+Double.parseDouble(dlspeed));UKUP=(UKUP+Double.parseDouble(upspeed));UKPG=(UKPG+Double.parseDouble(aping));UKJT=(UKJT+Double.parseDouble(ajitter));UKCount++;}
                   if ("US".equals(BDcountry)){USDL=(USDL+Double.parseDouble(dlspeed));USUP=(USUP+Double.parseDouble(upspeed));USPG=(USPG+Double.parseDouble(aping));USJT=(USJT+Double.parseDouble(ajitter));USCount++;}
                   if ("Costa Rica".equals(BDcountry)){CostaRicaDL=(CostaRicaDL+Double.parseDouble(dlspeed));CostaRicaUP=(CostaRicaUP+Double.parseDouble(upspeed));CostaRicaPG=(CostaRicaPG+Double.parseDouble(aping));CostaRicaJT=(CostaRicaJT+Double.parseDouble(ajitter));CostaRicaCount++;}
                   if ("Spain".equals(BDcountry)){SpainDL=(SpainDL+Double.parseDouble(dlspeed));SpainUP=(SpainUP+Double.parseDouble(upspeed));SpainPG=(SpainPG+Double.parseDouble(aping));SpainJT=(SpainJT+Double.parseDouble(ajitter));SpainCount++;}
                   
                    }
                
                //Adding Average speeds to countries
         if (ChinaCount!=0){ChinaDL=ChinaDL/ChinaCount;ChinaUP=ChinaUP/ChinaCount;ChinaPG=ChinaPG/ChinaCount;ChinaJT=ChinaJT/ChinaCount; }
         if (IndiaCount!=0){IndiaDL=IndiaDL/IndiaCount;IndiaUP=IndiaUP/IndiaCount;IndiaPG=IndiaPG/IndiaCount;IndiaJT=IndiaJT/IndiaCount; }
         if (PhilippinesCount!=0){PhilippinesDL=PhilippinesDL/PhilippinesCount;PhilippinesUP=PhilippinesUP/PhilippinesCount;PhilippinesPG=PhilippinesPG/PhilippinesCount;PhilippinesJT=PhilippinesJT/PhilippinesCount; }
         if (RomaniaCount!=0){RomaniaDL=RomaniaDL/RomaniaCount;RomaniaUP=RomaniaUP/RomaniaCount;RomaniaPG=RomaniaPG/RomaniaCount;RomaniaJT=RomaniaJT/RomaniaCount; }
         if (SouthACount!=0){SouthADL=SouthADL/SouthACount;SouthAUP=SouthAUP/SouthACount;SouthAPG=SouthAPG/SouthACount;SouthAJT=SouthAJT/SouthACount; }
         if (SriLankaCount!=0){SriLankaDL=SriLankaDL/SriLankaCount;SriLankaUP=SriLankaUP/SriLankaCount;SriLankaPG=SriLankaPG/SriLankaCount;SriLankaJT=SriLankaJT/SriLankaCount;} 
         if (TurkeyCount!=0){TurkeyDL=TurkeyDL/TurkeyCount;TurkeyUP=TurkeyUP/TurkeyCount;TurkeyPG=TurkeyPG/TurkeyCount;TurkeyJT=TurkeyJT/TurkeyCount;}
         if (UKCount!=0){UKDL=UKDL/UKCount;UKUP=UKUP/UKCount;UKPG=UKPG/UKCount;UKJT=UKJT/UKCount; }
         if (USCount!=0){USDL=USDL/USCount;USUP=USUP/USCount;USPG=USPG/USCount;USJT=USJT/USCount; }
         if (CostaRicaCount!=0){CostaRicaDL=CostaRicaDL/CostaRicaCount;CostaRicaUP=CostaRicaUP/CostaRicaCount;CostaRicaPG=CostaRicaPG/CostaRicaCount;CostaRicaJT=CostaRicaJT/CostaRicaCount; }
         if (SpainCount!=0){SpainDL=SpainDL/SpainCount;SpainUP=SpainUP/SpainCount;SpainPG=SpainPG/SpainCount;SpainJT=SpainJT/SpainCount; }
         if (PolandCount!=0){PolandDL=PolandDL/PolandCount;PolandUP=PolandUP/PolandCount;PolandPG=PolandPG/PolandCount;PolandJT=PolandJT/PolandCount;}
         
         //Creating countries lists to hold Bandwidth
         List<Double> polandBD=new ArrayList<Double>();List<Double> RomaniaBD=new ArrayList<Double>();List<Double> SpainBD=new ArrayList<Double>();List<Double> CostaRicaBD=new ArrayList<Double>();
         List<Double> USBD=new ArrayList<Double>();List<Double> UKBD=new ArrayList<Double>();List<Double> TurkeyBD=new ArrayList<Double>();List<Double> SriLankaBD=new ArrayList<Double>();List<Double> SouthABD=new ArrayList<Double>();
         List<Double> PhilippinesBD=new ArrayList<Double>();List<Double> IndiaBD=new ArrayList<Double>();List<Double> ChinaBD=new ArrayList<Double>();
         List<List> bandwidth =new ArrayList<List>();
         //Adding values to lists
         
         polandBD.add(round(PolandDL,2));polandBD.add(round(PolandUP,2));polandBD.add(round(PolandPG,2));polandBD.add(round(PolandJT,2));
         SpainBD.add(round(SpainDL,2));SpainBD.add(round(SpainUP,2));SpainBD.add(round(SpainPG,2));SpainBD.add(round(SpainJT,2));
         CostaRicaBD.add(round(CostaRicaDL,2));CostaRicaBD.add(round(CostaRicaUP,2));CostaRicaBD.add(round(CostaRicaPG,2));CostaRicaBD.add(round(CostaRicaJT,2));
         USBD.add(round(USDL,2));USBD.add(round(USUP,2));USBD.add(round(USPG,2));USBD.add(round(USJT,2));
         UKBD.add(round(UKDL,2));UKBD.add(round(UKUP,2));UKBD.add(round(UKPG,2));UKBD.add(round(UKJT,2));
         TurkeyBD.add(round(TurkeyDL,2));TurkeyBD.add(round(TurkeyUP,2));TurkeyBD.add(round(TurkeyPG,2));TurkeyBD.add(round(TurkeyJT,2));
         SriLankaBD.add(round(SriLankaDL,2));SriLankaBD.add(round(SriLankaUP,2));SriLankaBD.add(round(SriLankaPG,2));SriLankaBD.add(round(SriLankaJT,2));
         SouthABD.add(round(SouthADL,2));SouthABD.add(round(SouthAUP,2));SouthABD.add(round(SouthAPG,2));SouthABD.add(round(SouthAJT,2));
         RomaniaBD.add(round(RomaniaDL,2));RomaniaBD.add(round(RomaniaUP,2));RomaniaBD.add(round(RomaniaPG,2));RomaniaBD.add(round(RomaniaJT,2));
         PhilippinesBD.add(round(PhilippinesDL,2));PhilippinesBD.add(round(PhilippinesUP,2));PhilippinesBD.add(round(PhilippinesPG,2));PhilippinesBD.add(round(PhilippinesJT,2));
         IndiaBD.add(round(IndiaDL,2));IndiaBD.add(round(IndiaUP,2));IndiaBD.add(round(IndiaPG,2));IndiaBD.add(round(IndiaJT,2));
         ChinaBD.add(round(ChinaDL,2));ChinaBD.add(round(ChinaUP,2));ChinaBD.add(round(ChinaPG,2));ChinaBD.add(round(ChinaJT,2));
         polandBD.add(round(PolandDL,2));polandBD.add(round(PolandUP,2));polandBD.add(round(PolandPG,2));polandBD.add(round(PolandJT,2));
         //Adding countries lists to big list
         bandwidth.add(ChinaBD);
         bandwidth.add(IndiaBD);
         bandwidth.add(PhilippinesBD);
         bandwidth.add(polandBD);
         bandwidth.add(RomaniaBD);
         bandwidth.add(SouthABD);
         bandwidth.add(SriLankaBD);
         bandwidth.add(TurkeyBD);
         bandwidth.add(UKBD);
         bandwidth.add(USBD);
         bandwidth.add(CostaRicaBD);
         bandwidth.add(SpainBD);
        
         
         return bandwidth;
	}
        
        public void listUsers(String host,String userid,String getCountry,String getLocation,String getDepartament,int offset) throws Exception{
	    CallableStatement callableStatement = null;	
            try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}catch(ClassNotFoundException e){}
		Connection con = DriverManager.getConnection(url);
		String getUsers = "{call checkInstalls(?,?,?,?,?,?)}";
                callableStatement = con.prepareCall(getUsers);
                callableStatement.setString(1, userid+"%");
                callableStatement.setString(2, host+"%");
                callableStatement.setString(3, getDepartament+"%");
                callableStatement.setString(4, getCountry+"%");
                callableStatement.setString(5, getLocation+"%");
                callableStatement.setInt(6, offset);
		ResultSet rs= callableStatement.executeQuery();
                        while(rs.next()& count<30){
                        uid[count] = rs.getString("EmployeeID");
                        name[count] = rs.getString("EmployeeName");
			departamentID[count]=rs.getString("DeptID");
                        departament[count] = rs.getString("DeptName");
                        country[count]= rs.getString("Country");
                        location[count] = rs.getString("Location");
                        hostname[count]=rs.getString("Hostname");
                        clientip[count] = rs.getString("IP_Address");
                        Date dataParsata = parser.parse(rs.getString("Created_Date"));
                        date[count] = formatter.format(dataParsata);
                        count++;
		}
                
                
	}
        
        public String[] tryLogin(String username,String password) throws Exception{
            
            CallableStatement callableStatement = null;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}catch(ClassNotFoundException e){}
		Connection con = DriverManager.getConnection(url);
                
		String dbCheckLogin = "{call checklogin(?,?)}";
                String dbSetLocked="{call setLocked(?,?,?)}";
                
                callableStatement = con.prepareCall(dbCheckLogin);
                callableStatement.setString(1, username);
                callableStatement.setString(2, password);
                ResultSet rs= callableStatement.executeQuery();
		if(rs.next()){
                login[0] = rs.getString("islocked");
                
                   if(login[0].equals("yes")){login[0]="false";login[1]="Your account is locked";}else{ login[0]="true";login[1]="Account is ok"; }
                }else {callableStatement = con.prepareCall("{call getLocked(?)}");callableStatement.setString(1, username) ;ResultSet res=callableStatement.executeQuery();if(res.next()){attempts=res.getInt("loginAttempts"); System.out.println("attempts before if "+attempts);System.out.println(password);};login[0]="false";login[1]="Username or password incorrect";  if(attempts<10){attempts++; callableStatement = con.prepareCall(dbSetLocked);callableStatement.setString(1, username);callableStatement.setString(2, "no");callableStatement.setInt(3, attempts);callableStatement.execute();}else{attempts++; callableStatement = con.prepareCall(dbSetLocked);callableStatement.setString(1, username);callableStatement.setString(2, "yes");callableStatement.setInt(3, attempts);callableStatement.execute();}    }
                return login;          
        }
        
        public boolean validateString(String tovalidate){
        boolean isValid = false;
        if (tovalidate.contains("?") || tovalidate.contains(";") || tovalidate.contains(":") || tovalidate.contains(")") || tovalidate.contains("(") || tovalidate.contains("/") || tovalidate.contains(">") || tovalidate.contains("<") || tovalidate.contains("'") || tovalidate.contains("-")|| tovalidate.contains("`") || tovalidate.contains("%")|| tovalidate.contains("|") || tovalidate.contains("^")|| tovalidate.contains("~") || tovalidate.length()>20)
        {isValid=false;} else {isValid=true;}
        
            return isValid;
        } 
        
        public String generateToken(){
        
    Random rand = new Random(); 
    long value = rand.nextLong();
    String token=String.valueOf(value);
        // Print random integers 
      return token;
    }
        public String[] getHostname(){
		return hostname;
	}
	public String[] getUid(){
		return uid;
	}
	public String[] getEventName(){
		return eventname;
	}
	public String[] getDate(){
		return date;
	}
	public String[] getIp(){
		return clientip;
	}
        public String[] getLogs(){
		return logs;
	}
        public String[] getSerialNumber(){
		return serialnumber;
	}
	
        public int getCount(){
		return count;
	}
        public String[] getCountry(){
		return country;
	}
         public String[] getName(){
		return name;
	}
          public String[] getEmployee(){
		return employee;
	}
          public String[] getDepartament(){
		return departament;
	}
           public String[] getDepID(){
		return departamentID;
	}
            public String[] getLocation(){
		return location;
	}
        public String[] getNetworkInfo(){
		return NetworkInfo;
	}
        public String[] getDLSpeed(){
		return DLSpeed;
	}
        public String[] getUPSeed(){
		return UPSpeed;
	}
        public Integer[] getPing(){
		return Ping;
	}
        public String[] getJitter(){
		return Jitter;
	}
        public String[] getWanip(){
		return wanip;
	}
        public String[] getIsp(){
		return isp;
	}
        public String[] itIsRunned(){
		return itIsRunned;
	}
        
        
        public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

          BigDecimal bd = BigDecimal.valueOf(value);
          bd = bd.setScale(places, RoundingMode.HALF_UP);
          return bd.doubleValue();
}
        
        
        
        
}
