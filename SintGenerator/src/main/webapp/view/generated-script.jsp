<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Generated Script</title>
    <link rel="stylesheet" href="<c:url value="../css/generated-script.css"/>"/>
    <!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.js" type="text/javascript"></script>-->
    <script src="/js/jquery-3.0.0.min.js" type="text/javascript"></script>
</head>
<body>
    <div id="container">
        <div id="center-cont">
            <form action="/WriteClipboard" method="get">
                <input type="text" name="cb-text" value="${resScript}" hidden>
                <input type="submit" value="Copy to Clipboard" id="copy-btn">
            </form>

            <pre>${resScript}</pre>
        </div>
    </div>

    <script>
        $('input#copy-btn').click(function(){
            alert('Script in Clipboard!');
        });
    </script>
</body>
</html>