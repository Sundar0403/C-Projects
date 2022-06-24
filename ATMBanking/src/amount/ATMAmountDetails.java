package amount;

public class ATMAmountDetails 
{
	double amount;
	int noteNos;
	double total;
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getNoteNos() {
		return noteNos;
	}
	public void setNoteNos(int noteNos) {
		this.noteNos = noteNos;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal() {
		total=amount*noteNos;
	}
	@Override
	public String toString() {
		return "ATMAmountDetails [amount=" + amount + ", noteNos=" + noteNos + ", total=" + total + "]";
	}
	
	
}
