const express = require("express");
const app = express();

app.get("/calc", (req, res) => {

    const expression = req.query.exp;

    // SECURITY ISSUE: Arbitrary Code Execution
    const result = eval(expression);

    res.send("Result: " + result);
});

app.listen(3000);

