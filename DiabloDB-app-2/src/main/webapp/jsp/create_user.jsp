<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>DiabloDB: the forum for nothing</title>

    <!-- Custom styles for this template -->
    <link href="../css/create_page.css" rel="stylesheet">

  </head>

  <body role="document">

    <!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/poster">DiabloDB <span class="glyphicon glyphicon-fire" aria-hidden="true"></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="/poster?action=getUsers">All users</a></li>
            <li><a href="/poster?action=getThreads">All threads</a></li>
            <li><a href="/poster?action=getComments">All comments</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container theme-showcase" role="main">

        <!-- JSP -->
        <form action="/poster" method="post"  role="form" data-toggle="validator" >
          <input type="hidden" id="action" name="action" value="createUser">
          <h2>Create User</h2>
          <c:choose>
            <c:when test="${not empty message}">
                <div class="alert alert-info"> ${message} </div>
            </c:when>
            <c:otherwise>
              <fieldset class="form-group">
                <label class="control-label col-xs-4">User name:</label>
                <input type="text" name="userName" id="userName" class="form-control" value="${poster.userName}" required="true"/>
              </fieldset>
              <fieldset class="form-group">
                <label class="control-label col-xs-4">Admin password (only admins can create users):</label>
                <input type="password" name="adminPass" id="adminPass" class="form-control" value="${adminPass}" required="true"/>
              </fieldset>
              <br></br>
              <button type="submit" class="btn btn-primary  btn-md">Create</button>
            </c:otherwise>   
          </c:choose>                                               
        </form>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
  </body>
</html>
