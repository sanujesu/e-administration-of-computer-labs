using System;
using System.Collections.Generic;
using System.Text;

namespace Algorithm
{
    public class Thongke
    {
        private int _totalMove;
        private int _bestCost;

        public int TotalMove
        {
            get { return _totalMove; }
            set { _totalMove = value; }
        }

        public int BestCost
        {
            get { return _bestCost; }
            set { _bestCost = value; }
        }
    }
}
