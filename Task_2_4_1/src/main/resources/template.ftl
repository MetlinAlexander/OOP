<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Task Results</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        h2 {
            text-align: center;
            margin-top: 20px;
            color: #333;
        }
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f9f9f9;
        }
        p {
            margin: 4px 0;
        }
        .red {
            color: red;
            font-weight: bold;
        }
        .yellow {
            color: orange;
            font-weight: bold;
        }
        .green {
            color: green;
            font-weight: bold;
        }
        .check-icon {
            color: green;
        }
        .cross-icon {
            color: red;
        }
        .mark {
            font-size: 1.2em;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h2>Task Results</h2>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <#list tasks as task>
                    <th>${task.id}</th>
                </#list>
            </tr>
        </thead>
        <tbody>
            <#list tasksResults as student, tasks>
                <tr>
                    <td>${student.passportName}</td>
                    <#list tasks as task, result>
                        <td>
                            <p>Downloaded successfully: <#if result.downloaded><span class="check-icon">&#10004;</span><#else><span class="cross-icon">&#10008;</span></#if></p>
                            <p>Compiled successfully: <#if result.compiled><span class="check-icon">&#10004;</span><#else><span class="cross-icon">&#10008;</span></#if></p>
                            <p>Javadoc generated: <#if result.javadoc><span class="check-icon">&#10004;</span><#else><span class="cross-icon">&#10008;</span></#if></p>
                            <p>Checkstyle warning count:
                                <#if result.checkstyleWarnings == 0>
                                    <span class="green">${result.checkstyleWarnings}</span>
                                <#elseif result.checkstyleWarnings <= 10>
                                    <span class="yellow">${result.checkstyleWarnings}</span>
                                <#else>
                                    <span class="red">${result.checkstyleWarnings}</span>
                                </#if>
                            </p>
                            <p>Tests Passed/Skipped/Failed:
                                <span class="green">${result.testsPassed}</span>/
                                <span class="yellow">${result.testsSkipped}</span>/
                                <span class="red">${result.testsFailed}</span>
                            </p>
                            <p>Deadlines:
                                <#if result.softDeadline>
                                    <span class="green"><b>soft</b></span>
                                <#else>
                                    <span class="red"><b>soft</b></span>
                                </#if>
                                <#if result.hardDeadline>
                                    <span class="green"><b>hard</b></span>
                                <#else>
                                    <span class="red"><b>hard</b></span>
                                </#if>
                            </p>
                            <p class="mark">Mark: <b>${result.mark}</b></p>
                        </td>
                    </#list>
                </tr>
            </#list>
        </tbody>
    </table>
</body>
</html>
