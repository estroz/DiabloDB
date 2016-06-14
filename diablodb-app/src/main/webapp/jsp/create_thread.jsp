<!DOCTYPE html>
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
    <link href="/jsp/../css/create_thread.css" rel="stylesheet">

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
          <a class="navbar-brand" href="../index.jsp">DiabloDB <span class="glyphicon glyphicon-fire" aria-hidden="true"></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="/jsp/pages_list.jsp">Your Pages</a></li>
            <li><a href="/jsp/threads_list.jsp">Your Threads</a></li>
            <li><a href="/jsp/comments_list.jsp">Your Comments</a></li>
            <li><a href="/jsp/user.jsp">Profile</a></li>
          </ul>
                      <ul class="nav navbar-nav navbar-right">
            <li><a id="login" href="/jsp/sign_in.jsp">Log in</a></li>
            <!-- <li><a id="logout" href="/jsp/sign_in.jsp">Log out</a></li> -->
            <!-- <li class="dropdown">
              <a href="/jsp/#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="/jsp/#">Action</a></li>
                <li><a href="/jsp/#">Another action</a></li>
                <li><a href="/jsp/#">Something else here</a></li>
                <li role="separator" class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="/jsp/#">Separated link</a></li>
                <li><a href="/jsp/#">One more separated link</a></li>
              </ul>
            </li> -->
          </ul>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container theme-showcase" role="main">

      <fieldset class="form-group">
        <label for="exampleTextarea">Thread title:</label>
        <textarea class="form-control" rows="2"></textarea>
      </fieldset>

      <fieldset class="form-group">
        <label for="exampleTextarea">Thread description:</label>
        <textarea class="form-control" rows="4"></textarea>
      </fieldset>
      <a href="../index.jsp"><button class="btn btn-primary">Create</button></a>

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
