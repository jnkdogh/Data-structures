package col106.assignment3.Election;

public class Candidate{
	public String name, candID, state, district, constituency, party, votes;

	public Candidate(String name, String candID, String state, String district, String constituency, String party, String votes){
		this.name = name; 
		this.candID = candID; 
		this.state = state; 
		this.district = district;
		this.constituency = constituency;
		this.party = party; 
		this.votes = votes;
	}
}