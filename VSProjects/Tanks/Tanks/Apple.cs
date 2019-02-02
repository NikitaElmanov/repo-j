using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tanks.Interfaces;

namespace Tanks
{
    public class Apple : IPicture
    {
        AppleImg appleImg = new AppleImg();
        Image img;

        int x, y;

        public Image Img
        {
          get { return img; }
        }

        public Apple(int x, int y)
        {
            img = appleImg.Img;

            this.x = x;
            this.y = y;
        }

        public int Y
        {
            get { return y; }
        }

        public int X
        {
            get { return x; }
        }
    }
}
