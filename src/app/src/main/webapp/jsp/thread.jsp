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
    <link href="../css/thread.css" rel="stylesheet">

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

      <div class="page-header">
        <h3>Thread: ${title}<h3>
      </div>

      <div class="sidebar">
        <a href="jsp/create_comment.jsp?threadID=${threadID}&title=${title}"><button class="btn btn-primary">Post a comment</button></a>
      </div>

      <div class="row">
        <div class="col-md-12">

          <!-- ADDED FOR JSP -->
          <!-- List of pages -->
          <form action="/poster" method="get" id="commentForm" role="form" >
            <c:if test="${not empty message}">
                <div class="alert alert-info"> ${message} </div>
            </c:if>             
            <c:choose>
              <c:when test="${not empty comments}">
                <table  class="table table-striped">
                  <thead>
                      <tr>
                          <td></td>
                          <td>Votes</td>
                          <td>Comment</td>
                          <td>Poster</td>
                      </tr>
                  </thead>
                  <c:forEach var="comment" items="${comments}">
                      <tr>
                          <td>
                            <button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal${comment.commID}">Vote</button>
                            <!-- Modal -->
                            <div class="modal fade" id="myModal${comment.commID}" role="dialog">
                              <div class="modal-dialog">
                              
                                <!-- Modal content-->
                                <div class="modal-content">
                                  <div class="modal-header">
                                    <h4 class="modal-title">Vote</h4>
                                  </div>
                                  <div class="modal-body">
                                    <!-- JSP -->
                                    <form action="/poster" method="post"  role="form" data-toggle="validator" >
                                      <input type="hidden" id="action" name="action" value="createVote">
                                      <input type="hidden" id="isComment" name="isComment" value="true">
                                      <input type="hidden" id="id" name="id" value="${comment.commID}">
                                      <input type="hidden" id="threadID" name="threadID" value="${comment.threadID}">
                                      <input type="hidden" id="title" name="title" value="<%= request.getParameter("title")%>">
                                      <fieldset class="form-group">
                                        <label class="control-label col-xs-4">Poster name:</label>
                                        <input type="text" name="posterName" id="posterName" class="form-control" required="true"/>
                                      </fieldset>
                                      <fieldset>                                   
                                        <div class="radio">
                                          <label><input type="radio" name="voteType" value="true">Upvote</label>
                                        </div>
                                        <div class="radio">
                                          <label><input type="radio" name="voteType" value ="false">Downvote</label>
                                        </div> 
                                      </fieldset>
                                        <br></br>
                                        <button type="submit" class="btn btn-primary  btn-md">Vote</button> 
                                    </form>
                                  </div>
                                </div>
                                
                              </div>
                            </div>
                            <!-- /Modal -->
                          </td>
                          <td>${comment.voteNum}</td>
                          <td>${comment.text}</td>
                          <td>
                            <c:choose>
                              <c:when test="${comment.poster != null}">
                                ${comment.poster}
                              </c:when>    
                              <c:otherwise>
                                [deleted]
                              </c:otherwise>
                            </c:choose>
                          </td>
                      </tr>
                  </c:forEach>
                </table>
               </c:when>                    
                  <c:otherwise>
                      <br>           
                      <div class="alert alert-info">
                          No comments.
                      </div>
                  </c:otherwise>
            </c:choose>
          </form>
          <!-- ADDED FOR JSP STUFF -->

        </div>
      </div>

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
