{% extends 'account/base.html' %}
<%@include file="base.jsp"%>

{% block title %}
 作家专区
{% endblock %}

{% block head %}

{% endblock %}

{% block content-header %}
<h1>开通作家</h1>
{% endblock %}

{% block content %}
<div class="row">
    <div class="col-md-8">
        <div class="box box-success">
            <div class="box-header">

            </div>
            <div class="box-body">
                <form action="" method="post">
                    {% csrf_token %}
                    <div class="form-group">
                        <label for="pen_name"><h3>请输入笔名</h3></label>
                        <input type="text" class="form-control" id="pen_name" name="pen_name">
                    </div>
                    <div class="form-group">
                        <input type="submit" value="开通" class="btn btn-success">
                    </div>
                    <div class="form-group">
                        {% for message in messages %}
                            {{ message }}
                        {% endfor %}
                    </div>
                </form>
            </div>
            <div class="box-footer">

            </div>
        </div>
    </div>
</div>
{% endblock %}