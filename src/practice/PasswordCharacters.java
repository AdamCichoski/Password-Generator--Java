package practice;

public class PasswordCharacters {
	private final String UPPERCASE = "ABCDEFGHIJKLMNOPQRZTUVWXYZ";
	private final String SPECIAL_CHARACTERS = "~`! @#$%^&*()_-+={[}]|\\:;\"'<,>.?/";
	private String password;
	private boolean needsCapitals, needsSpecialCharacters;
	public PasswordCharacters(){
		
	}
	
	/**
	 * This checks the strength of the inputted password given
	 * @param password
	 * @param neededLength
	 * @param needsCapitals
	 * @param needsSpecialCharacters
	 */
	public boolean checkPassword(String password, boolean needsCapitals, boolean needsSpecialCharacters) {
		int points = (needsCapitals)? 1:0;
		points+= (needsSpecialCharacters)? 1:0;
		int count=0;
		if(needsCapitals) {
			count+=(contains(password,UPPERCASE))? 1:0;
		}
		if(needsSpecialCharacters) {
			count+=(contains(password,SPECIAL_CHARACTERS))? 1:0;
		}
		return points == count;
	}
	
	/**
	 * This method is used to check 
	 * @param password
	 * @param index
	 * @return
	 */
	private boolean contains(String password ,String index) {
		for(int i=0;i<password.length();i++) {
			for (int j=0;j<index.length();j++) {
				if (password.charAt(i) == index.charAt(i)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * This returns a string of the instance variables
	 */
	@Override
	public String toString() {
		return this.UPPERCASE +" "+ this.SPECIAL_CHARACTERS;
	}
}
