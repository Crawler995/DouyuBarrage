from DyBarrageCrawler import *
import sys

if __name__ == "__main__":
    dy_barrage_crawler = DyBarrageCrawler(sys.argv[1])
    dy_barrage_crawler.start()
