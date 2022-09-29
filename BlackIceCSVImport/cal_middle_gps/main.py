import sqlalchemy

engine = sqlalchemy.create_engine('mysql+pymysql://blackice:"DDNS 주소와 포트번호"/black_ice')
connection = engine.connect()
metadata = sqlalchemy.MetaData()
table = sqlalchemy.Table('blackice_area', metadata, autoload=True, autoload_with=engine)

select_query = sqlalchemy.select([table])

select_result_proxy = connection.execute(select_query)
result_set = select_result_proxy.fetchall()

table = sqlalchemy.Table('area', metadata, autoload=True, autoload_with=engine)

for i in range(len(result_set)):
    temp_tuple = result_set[i]
    temp_list = [temp_tuple[0], temp_tuple[1], temp_tuple[2], temp_tuple[3], temp_tuple[4]]
    temp_lat = round((temp_tuple[6] + temp_tuple[8]) / 2, 7)
    temp_lng = round((temp_tuple[7] + temp_tuple[9]) / 2, 7)
    temp_list.append(temp_lat)
    temp_list.append(temp_lng)

    insert_query = sqlalchemy.insert(table).values(temp_list)
    insert_result_proxy = connection.execute(insert_query)
    insert_result_proxy.close()
