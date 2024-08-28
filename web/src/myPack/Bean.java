package myPack;

public class Bean {
	String name="";
	String uid="";
        
	public static void main(String[] args) {
	}
	public void setName(String username){
		name=username;
	}

	public String getName(){
		return name;
	}
	public void removeName(){
		name="";
	}
        public void setUid(String topuserid){
		uid=topuserid;
	}

	public String getUid(){
		return uid;
	}
	public void removeUid(){
		uid="";
	}
        
}
