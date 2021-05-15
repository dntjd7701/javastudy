package final03;

public class Money {
	private int amount;
	
	
	public Money(int amount) {
		this.amount = amount;
	}
	
	
	// ex) two.add(three)의 경우,
	// two.add(three) ==> 5 
	public Money add(Money money) {
		return new Money(amount + money.amount);
	}
	// 3과 같야하 하므로.
	// five.minus(two)
	public Money minus(Money money) {
		return new Money(amount - money.amount);
	}


	public Money mutiply(Money money) {
		return new Money(amount * money.amount);
		
	}
	
	public Money devide(Money money) {
		return new Money(amount / money.amount);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		if (amount != other.amount)
			return false;
		return true;
	}

}
