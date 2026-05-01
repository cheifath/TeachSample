import sqlite3

def find_user_by_name(username):
    # Connect to an in-memory database
    conn = sqlite3.connect(':memory:')
    cursor = conn.cursor()

    # Create a dummy table and insert some data
    cursor.execute("CREATE TABLE users (id INTEGER, username TEXT, role TEXT, secret_key TEXT)")
    cursor.execute("INSERT INTO users VALUES (1, 'admin', 'admin', 'super_secret_admin_hash')")
    cursor.execute("INSERT INTO users VALUES (2, 'john_doe', 'user', 'johns_normal_hash')")
    
    # THE VULNERABILITY: Directly inserting user input into the SQL query string
    query = f"SELECT id, username, role FROM users WHERE username = '{username}'"
    
    print(f"Executing Query: {query}")
    
    try:
        cursor.execute(query)
        results = cursor.fetchall()
        return results
    except sqlite3.Error as e:
        print(f"Database error: {e}")
    finally:
        conn.close()

# --- DEMONSTRATION ---

print("--- Normal Usage ---")
# Works as intended, fetching only john_doe's public info
print(find_user_by_name("john_doe")) 

print("\n--- SQL Injection Attack ---")
# The malicious input tricks the database into ignoring the username check
malicious_input = "john_doe' OR '1'='1"
print(find_user_by_name(malicious_input))