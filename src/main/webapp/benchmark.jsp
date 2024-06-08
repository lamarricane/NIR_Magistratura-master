<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Book"%>
<html>
    <head>
        <title> Benchmark </title>

	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>

        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page isELIgnored="false"%>
        <link rel="stylesheet" type="text/css" href="style.css"/>
            <style>
            .text-field {
              margin-bottom: 1rem;
              border: 3px
            }

            .text-field__label {
              border: 3px
              display: block;
              margin-bottom: 0.25rem;
              margin: 1px 1px 1px 1px;
            }

            .text-field__input {
              margin: 2px 2px 2px 2px;
              display: block;
              width: 20%;
              height: calc(2.25rem + 2px);
              padding: 0.375rem 0.75rem;
              font-family: inherit;
              font-size: 1rem;
              font-weight: 100;
              line-height: 1.5;
              color: #212529;
              background-color: #fff;
              background-clip: padding-box;
              border: 1px solid #bdbdbd;
              border-radius: 0.25rem;
              transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }
            button.button7 {
              font-weight: 700;
              color: white;
              text-decoration: none;
              padding: .8em 1em calc(.8em + 3px);
              border-radius: 3px;
              background: rgb(64,199,129);
              box-shadow: 0 -3px rgb(53,167,110) inset;
              transition: 0.2s;
            }
            button.button7:hover { background: rgb(53, 167, 110); }
            button.button7:active {
              background: rgb(33,147,90);
              box-shadow: 0 3px rgb(33,147,90) inset;
            }
            .row {
            	white-space: normal;
            	display: inline-block;
            	width: 50%;
            	vertical-align: top;
            	margin-right: 5%;
            	font-size: 14px;
            }
            .form-group {
              display: flex;
              flex-direction: row;
              justify-content: center;
              align-items: center;
              margin: 3px 3px 0 0;
              width: 90%;
              height: 7%;
            }
            label {
                font: 14pt Impact;
                color: black;
            }
            </style>



    </head>
<body style="background-image: url('change_book.jpg');">
<%
    List<Book> books = (List) request.getAttribute("books");
%>
    <div class="container">
        <div class="row" align="center">
            <table class="table_dark">
                <thread>
                    <tr>
                        <th>ID Book</th>
                        <th>Name</th>
                        <th>Genre</th>
                        <th>Author</th>
                        <th>Publisher</th>
                        <th>Number of pages</th>
                        <th>Year of publishing</th>
                    </tr>
                </thread>
                <body>
                    <% for(Book book : books) { %>
                        <tr>
                            <td style="text-align:center"> <%= book.getId() %> </td>
                            <td style="text-align:center"> <%= book.getName() %> </td>
                            <td style="text-align:center"> <%= book.getGenre() %> </td>
                            <td style="text-align:center"> <%= book.getAuthor().getName() %> </td>
                            <td style="text-align:center"> <%= book.getPublisher().getName() %> </td>
                            <td style="text-align:center"> <%= book.getNumberOfPages() %> </td>
                            <td style="text-align:center"> <%= book.getYearOfPublishing() %> </td>
                        <tr>
                    <% } %>
                </body>
            </table>
        </div>
        <div class="column">
            <form method="post" action="benchmark">
                <div class="text-field">
                                    <div class ="form-group">
                                        <label>Find By First Letter:</label>
                                        <input type="text" name="findByFirstLetter" class="text-field__input" placeholder="first letter">
                                        <button type="submit" name="action" value="findByFirstLetter" class="button7">Find By First Letter</button>
                                    </div>
                                    <div class ="form-group">
                                        <label>Find By Author:</label>
                                        <input type="text" name="findByAuthor" class="text-field__input" placeholder="author">
                                        <button type="submit" name="action" value="findByAuthor" class="button7">Find By Author</button>
                                    </div>
                                     <div class ="form-group">
                                        <label>Find By Publisher:</label>
                                        <input type="text" name="findByPublisher" class="text-field__input" placeholder="publisher">
                                        <button type="submit" name="action" value="findByPublisher" class="button7">Find By Publisher</button>
                                     </div>
                                     <div class ="form-group">
                                        <label>Find By Genre:</label>
                                        <input type="text" name="findByGenre" class="text-field__input" placeholder="genre">
                                        <button type="submit" name="action" value="findByGenre" class="button7">Find By Genre</button>
                                     </div>
                                     <div class ="form-group">
                                        <label>Find By Number of Pages:</label>
                                        <input type="text" name="term" class="text-field__input" placeholder="term">
                                        <input type="text" name="findByNumberOfPages" class="text-field__input" placeholder="number of pages">
                                        <button type="submit" name="action" value="findByNumberOfPages" class="button7">Find By Number of Pages</button>
                                     </div>
                                     <div class ="form-group">
                                         <label>Find By Year of Publishing:</label>
                                         <input type="text" name="term1" class="text-field__input" placeholder="term">
                                         <input type="text" name="findByYearOfPublishing" class="text-field__input" placeholder="year of publishing">
                                         <button type="submit" name="action" value="findByYearOfPublishing" class="button7">Find By Year of Publishing</button>
                                     </div>
                <div class="buttons">
                    <p align="center">
                        <button type="submit" name="action" value="generateBookList" class="button7">Generate Book List</button>
                        <button type="submit" name="action" value="deleteBookList" class="button7">Delete Book List</button>
                        <button type="submit" name="action" value="sortBookListByName" class="button7">Sort Book List By Name</button>
                        <button type="submit" name="action" value="sortBookListByYear" class="button7">Sort Book List By Year</button>
                        <button type="submit" name="action" value="sortBookListByNumberOfPages" class="button7">Sort Book List By Number of Pages</button>
                        <a href="/" class="button7">Back</a>
                    </p>
                </div>
            </form>
        </div>
    </div>
</body>
</html>