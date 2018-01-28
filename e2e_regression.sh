#!/bin/bash

bash get_all_answers.sh > all_answers_output.txt
diff all_answers_output.txt get_all_answers_regression.txt
sha1sum all_answers_output.txt
sha1sum get_all_answers_regression.txt
