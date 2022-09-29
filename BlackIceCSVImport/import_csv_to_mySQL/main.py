import pandas as pd
import sqlalchemy

excelSource = pd.read_excel("C:/Users/'사용자 폴더 이름'/Documents/GitHub/BlackIce/BlackIce_Backend/import_csv/BlackIceArea.xlsx",
                            "BlackIceArea")

connection_str = 'mysql+pymysql://blackice:"DDNS 주소와 포트 번호"/black_ice'
connection = sqlalchemy.create_engine(connection_str)
excelSource.to_sql(name='blackice_area', con=connection, if_exists='append', index=False)
