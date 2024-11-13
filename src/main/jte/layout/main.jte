@param gg.jte.Content content

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gui4Me</title>
    <link href="src/main/resources/static/css/styles.css" rel="stylesheet">
    <style>
body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

/* Add a black background color to the top navigation */
.navbar {
    background-color: #333;
    overflow: hidden;
  }
  
  /* Style the links inside the navigation bar */
  .navbar a {
    float: left;
    color: #f2f2f2;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
    font-size: 17px;
  }
  
  /* Change the color of links on hover */
  .navbar a:hover {
    background-color: #ddd;
    color: black;
  }
    </style>
</head>
<body>

    <!--  Navigation  -->
    <div class="navbar">
        <a href="/">Home</a>
        <a href="/users">Users</a>
        <a href="/products">Products</a>
        <a href="/market">Market</a>
    </div>

    <!--  Main  -->
    <main>
        ${content}
    </main>
</body>
</html>