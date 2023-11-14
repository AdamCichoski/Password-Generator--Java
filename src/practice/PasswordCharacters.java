package practice;
public class PasswordCharacters {
	private final String UPPERCASE = "ABCDEFGHIJKLMNOPQRZTUVWXYZ";
	private final String SPECIAL_CHARACTERS = "~`! @#$%^&*()_-+={[}]|\\:;\"'<,>.?/";
	private String password;
	private boolean needsCapitals, needsSpecialCharacters;

	public PasswordCharacters() {

	}

	/**
	 * This checks the strength of the inputted password given
	 * 
	 * @param password               is the inputted password
	 * @param neededLength           is minimum length
	 * @param needsCapitals          checks if capitals are needed
	 * @param needsSpecialCharacters checks is special characters are needed
	 */
	public boolean checkPassword(String password, int neededLength, boolean needsCapitals,
		boolean needsSpecialCharacters) {
		int points = (needsCapitals) ? 1 : 0;
		points += (needsSpecialCharacters) ? 1 : 0;
		int count = 0;
		count += ((needsCapitals && contains(password, UPPERCASE)) ^ contains(password, UPPERCASE)) ? 1 : 0;
		count += (needsSpecialCharacters && contains(password, SPECIAL_CHARACTERS) ^ contains(password, SPECIAL_CHARACTERS)) ? 1 : 0;
		return points <= count;
	}

	/**
	 * This method is used to check whether or not
	 * 
	 * @param password is the inputted password
	 * @param index is the value that is being checked
	 * @return if the password contains any value in the index
	 */
	private boolean contains(String password, String index) {
		for (int i=0;i<index.length();i++){
			if(password.indexOf(index.charAt(i))>-1){
				return true;
			}
		}
		return false;
	}


	/**
	 * This method generates a random password that is guaranteed to pass the
	 * strength test
	 * 
	 * @return the password as a string
	 */
	public String generatePassword() {
		StringBuffer password = new StringBuffer();
		for(int i=0;i<6;i++){
			password.append(UPPERCASE.charAt((int)(Math.random()*UPPERCASE.length()-1)));
			password.append(SPECIAL_CHARACTERS.charAt((int)(Math.random()*8)+2));
			password.append((char)((Math.random()*26)+97));
		}
		return password.toString();
	}

	/**
	 * This returns a string of the instance variables
	 */
	@Override
	public String toString() {
		return "Uppercase Characters: " + this.UPPERCASE + " Special Characters: " + this.SPECIAL_CHARACTERS;
	}
}
