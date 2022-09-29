import math


class Convert:
    RE = 6371.00877
    GRID = 5.0
    SLAT1 = 30.0
    SLAT2 = 60.0
    OLON = 126.0
    OLAT = 38.0
    XO = 43
    YO = 136

    def convert_gps(self, lat, lng):

        DEGRAD = math.pi / 180.0

        re = self.RE / self.GRID
        slat1 = self.SLAT1 * DEGRAD
        slat2 = self.SLAT2 * DEGRAD
        olon = self.OLON * DEGRAD
        olat = self.OLAT * DEGRAD

        sn = math.tan(math.pi * 0.25 + slat2 * 0.5) / math.tan(math.pi * 0.25 + slat1 * 0.5)
        sn = math.log(math.cos(slat1) / math.cos(slat2)) / math.log(sn)

        sf = math.tan(math.pi * 0.25 + slat1 * 0.5)
        sf = math.pow(sf, sn) * math.cos(slat1) / sn

        ro = math.tan(math.pi * 0.25 + olat * 0.5)
        ro = re * sf / math.pow(ro, sn)

        ra = math.tan(math.pi * 0.25 + lat * DEGRAD * 0.5)
        ra = re * sf / math.pow(ra, sn)

        theta = lng * DEGRAD - olon

        if theta > math.pi:
            theta -= 2.0 * math.pi

        if theta < -math.pi:
            theta += 2.0 * math.pi

        theta *= sn

        grid_x = math.floor(ra * math.sin(theta) + self.XO + 0.5)
        grid_y = math.floor(ro - ra * math.cos(theta) + self.YO + 0.5)

        grid_list = [grid_x, grid_y]

        return grid_list
