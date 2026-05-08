// vulnerable.js
// EDUCATIONAL PURPOSES ONLY

const express = require("express");
const mysql = require("mysql2");

const app = express();

const db = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "password",
    database: "testdb"
});

app.get("/login", (req, res) => {

    const username = req.query.username;
    const password = req.query.password;

    // SECURITY ISSUE: SQL Injection
    const query =
        `SELECT * FROM users 
         WHERE username = '${username}' 
         AND password = '${password}'`;

    db.query(query, (err, results) => {

        if (err) {
            return res.send(err.message);
        }

        if (results.length > 0) {
            return res.send("Login successful");
        }

        res.send("Invalid credentials");
    });
});

app.listen(3000, () => {
    console.log("Server running on port 3000");
});