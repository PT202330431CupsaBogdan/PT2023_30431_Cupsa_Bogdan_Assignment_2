import os
from os import listdir

# main

my_path_gui = './'

for file_name in listdir(my_path_gui):

    if file_name.endswith('.class'):
        os.remove(my_path_gui + file_name)


# gui

my_path_gui = './gui'

for file_name in listdir(my_path_gui):

    if file_name.endswith('.class'):
        os.remove(my_path_gui + '/' + file_name)

# model

my_path_model = './model'

for file_name in listdir(my_path_model):

    if file_name.endswith('.class'):
        os.remove(my_path_model + '/' + file_name)

# business logic

my_path_bl = './business_logic'

for file_name in listdir(my_path_bl):

    if file_name.endswith('.class'):
        os.remove(my_path_bl + '/' + file_name)