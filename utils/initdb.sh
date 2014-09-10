#!/bin/bash

user='testuser'
pass='testpwd'

mysql --user=$user --password=$pass < mysqldb.sql
