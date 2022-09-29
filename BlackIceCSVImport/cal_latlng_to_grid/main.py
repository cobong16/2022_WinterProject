import sqlalchemy
import convertGPS

engine = sqlalchemy.create_engine('mysql+pymysql://blackice:"DDNS 주소와 포트번호"/black_ice')
connection = engine.connect()
metadata = sqlalchemy.MetaData()
table1 = sqlalchemy.Table('area', metadata, autoload=True, autoload_with=engine)

select_query = sqlalchemy.select([table1])

select_result_proxy = connection.execute(select_query)
result_set = select_result_proxy.fetchall()

table2 = sqlalchemy.Table('area_temp', metadata, autoload=True, autoload_with=engine)

convert_gps = convertGPS.Convert()

for i in range(len(result_set)):
    temp_tuple = result_set[i]
    temp_list = list(temp_tuple)

    grid_list = convert_gps.convert_gps(float(temp_tuple[5]), float(temp_tuple[6]))

    temp_list.append(grid_list[0])
    temp_list.append(grid_list[1])

    print(temp_list)

    insert_query = sqlalchemy.insert(table2).values(temp_list)
    insert_result_proxy = connection.execute(insert_query)
    insert_result_proxy.close()

