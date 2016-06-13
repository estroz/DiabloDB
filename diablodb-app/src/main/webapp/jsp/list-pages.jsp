
    <div class="container theme-showcase" role="main">

      <div class="jumbotron">
        <h1>DiabloDB <span class="glyphicon glyphicon-fire" aria-hidden="true"></h1>
        <p>Your cloud based solution for machine intelligence content and delivery network.</p>
      </div>

      <div class="sidebar">
        <a href="create_page.html"><button class="btn btn-primary">Create Page</button></a>
                <a href="sign_up.html"><button class="btn btn-primary">Create Account</button></a>
      </div>

      <div class="row">
        <div class="col-md-12">
          <c:choose>
            <c:when test="${not empty pages}">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>Status</th>
                <th>Page</th>
                <%-- <th>Thread Name</th> --%>
                <th>Poster</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="page" items="${pages}">
                <c:set var="p" value="${page}">
              <tr>
                <td><span class="label label-success">Rising</span></td>
                <td><a href="page.html">${page.topicName}</a></td>
                <%-- <td><a href="page.html"><%=p %></a></td> --%>
                <td><a href="user.html">${page.posterName}</td></a>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </c:when>
        <c:otherwise>
          <br>
            <div class="alert alert-info">
              No pages exist currently.
            </div>
          </c:otherwise>
        </c:choose>
        </div>
      </div>


    </div> <!-- /container -->
<div class="container">
    <nav>
  <ul class="pagination">
    <li>
      <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li><a href="#">1</a></li>
    <li><a href="#">2</a></li>
    <li><a href="#">3</a></li>
    <li><a href="#">4</a></li>
    <li><a href="#">5</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
</div>
