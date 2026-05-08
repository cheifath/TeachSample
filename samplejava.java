PreparedStatement stmt = conn.prepareStatement(
    "SELECT * FROM users WHERE username = ? AND password = ?"
);

stmt.setString(1, username);
stmt.setString(2, password);
