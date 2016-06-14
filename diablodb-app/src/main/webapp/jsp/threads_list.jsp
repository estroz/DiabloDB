<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/images/favicon.ico">

    <title>DiabloDB: the forum for nothing</title>

    <!-- Custom styles for this template -->
    <link href="/css/threads_list.css" rel="stylesheet">

  </head>

  <body role="document">

    <!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <!-- <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button> -->
          <a class="navbar-brand" href="../index.jsp">DiabloDB <span class="glyphicon glyphicon-fire" aria-hidden="true"></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="/jsp/pages_list.jsp">Your Pages</a></li>
            <li class="active"><a href="/jsp/threads_list.jsp">Your Threads</a></li>
            <li><a href="/jsp/comments_list.jsp">Your Comments</a></li>
          </ul>
                      <ul class="nav navbar-nav navbar-right">
            <li><a href="/jsp/user.jsp">Profile</a></li>
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
      <div class="page-header text-center">
        <h3>Threads</h3>
      </div>

      <div class="row">
        <div class="col-md-12">
          <table class="table table-striped">
            <thead>
              <tr>
                <th class="col-sm-1 col-lg-1">Status</th>
                <th>Thread Name</th>
                <th class="text-right">Comments</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td class="col-sm-1 col-lg-1"><span class="label label-success">Rising</span></td>
                <td><a href="/jsp/thread.jsp">What stuff do you have in your closet?</a></td>
                <td class="text-right" style="padding-right:38px;"><% getThreads()% >x</td>
              </tr>
              <tr>
                <td class="col-sm-1 col-lg-1"><span class="label label-default">Neutral</span></td>
                <td><a href="/jsp/thread.jsp">Backhoes and fronthoes: the great debate</a></td>
                <td class="text-right" style="padding-right:38px;"><% getThreads()% >x</td>
              </tr>
              <tr>
                <td class="col-sm-1 col-lg-1"><span class="label label-success">Rising</span></td>
                <td><a href="/jsp/thread.jsp">Belligerence in the courtroom is a felony</a></td>
                <td class="text-right" style="padding-right:38px;"><% getThreads()% >x</td>
              </tr>
              <tr>
                <td class="col-sm-1 col-lg-1"><span class="label label-danger">Falling</span></td>
                <td><a href="/jsp/thread.jsp">2001: The Based God oddesy</a></td>
                <td class="text-right" style="padding-right:38px;"><% getThreads()% >x</td>
              </tr>

            </tbody>
          </table>
        </div>
      </div>

    </div> <!-- /container -->
    <div class="container">
        <nav>
      <ul class="pagination">
        <li>
          <a href="/jsp/#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <li><a href="/jsp/#">1</a></li>
        <li><a href="/jsp/#">2</a></li>
        <li><a href="/jsp/#">3</a></li>
        <li><a href="/jsp/#">4</a></li>
        <li><a href="/jsp/#">5</a></li>
        <li>
          <a href="/jsp/#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
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
