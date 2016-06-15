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

    <title>DiabloDB: the forum for something</title>

    <!-- Custom styles for this template -->
    <link href="../css/user.css" rel="stylesheet">

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
          </ul>           
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container theme-showcase" role="main">

      <button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">Query Theads</button>
        <!-- Modal -->
        <div class="modal fade" id="myModal" role="dialog">
          <div class="modal-dialog">
          
            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <h4 class="modal-title">Query threads. Get...</h4>
              </div>
              <div class="modal-body">
                <!-- JSP -->
                <form action="/poster" method="get"  role="form" data-toggle="validator" >
                  <input type="hidden" id="action" name="action" value="getQueriedThreads">
                  <input type="hidden" id="select" name="select" value="*">
                  <fieldset>                                   
                    <div class="radio">
                      <label><input type="radio" name="op" value=">">most popular threads.</label>
                    </div>
                    <div class="radio">
                      <label><input type="radio" name="op" value ="<">least popular threads.</label>
                    </div> 
                  </fieldset>
                    <br></br>
                    <button type="submit" class="btn btn-primary  btn-md">Submit</button> 
                </form>
              </div>
            </div>
            
          </div>
        </div>
        <!-- /Modal -->

      <div class="page-header">
        <h2>Threads</h2>
      </div>

      <div class="row">
        <div class="col-md-12">
          
          <!-- ADDED FOR JSP STUFF -->
          <form action="/poster" method="get" id="pageForm" role="form" >              
            <c:choose>
              <c:when test="${not empty threads}">
                <table  class="table table-striped">
                  <thead>
                      <tr>
                          <th>Thread ID</th>
                          <th>Title</th>
                          <th>Description</th>
                          <th>Votes</th>
                          <th>Page</th>
                          <th>Created by</th>
                      </tr>
                  </thead>
                  <c:forEach var="thread" items="${threads}">
                      <tr>
                          <td>${thread.threadID}</td>
                          <td>${thread.title}</td>
                          <td>${thread.text}</td>
                          <td>${thread.voteNum}</td>
                          <td>${thread.topicName}</td>
                          <td>${thread.posterName}</td>
                      </tr>
                  </c:forEach>
                </table>
               </c:when>                    
                  <c:otherwise>
                      <br>           
                      <div class="alert alert-info">
                          No threads.
                      </div>
                  </c:otherwise>
            </c:choose>
          </form>
          <!-- ADDED FOR JSP STUFF -->

        </div>
      </div>


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
