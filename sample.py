import sqlite3

def get_user_info(user_id):
    # Connect to a dummy in-memory database
    conn = sqlite3.connect(':memory:')
    cursor = conn.cursor()

    # Setup a dummy table for demonstration
    cursor.execute("CREATE TABLE users (id INTEGER, username TEXT, role TEXT)")
    cursor.execute("INSERT INTO users VALUES (1, 'alice', 'admin')")
    cursor.execute("INSERT INTO users VALUES (2, 'bob', 'user')")
    
    # 1. SYNTAX ERROR: Missing colon ':' at the end of the if statement
    if user_id is None
        print("Error: User ID cannot be None.")
        return

    # 2. SQL INJECTION PROBLEM: Using f-strings to concatenate user input directly into the query
    query = f"SELECT username, role FROM users WHERE id = {user_id}"
    
    print(f"Executing Query: {query}")
    
    try:
        cursor.execute(query)
        user_data = cursor.fetchall()
        return user_data
    except sqlite3.Error as e:
        print(f"Database error: {e}")
    finally:
        conn.close()

# Example execution (This will crash due to the syntax error first)
# result = get_user_info("1 OR 1=1") 
# print(result)