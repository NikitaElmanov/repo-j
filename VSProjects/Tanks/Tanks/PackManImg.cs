using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tanks
{
    class PackManImg
    {
        Image[] up = new Image[] 
        { 
           Properties.Resources.up_1,
           Properties.Resources.up_2,
           Properties.Resources.up_3,
           Properties.Resources.up_4,
           Properties.Resources.up_5
        };

        Image[] down = new Image[] 
        { 
           Properties.Resources.down_1,
           Properties.Resources.down_2,
           Properties.Resources.down_3,
           Properties.Resources.down_4,
           Properties.Resources.down_5
        };

        Image[] left = new Image[] 
        { 
           Properties.Resources.left_1,
           Properties.Resources.left_2,
           Properties.Resources.left_3,
           Properties.Resources.left_4,
           Properties.Resources.left_5,
        };

        Image[] right = new Image[] 
        { 
           Properties.Resources.right_1,
           Properties.Resources.right_2,
           Properties.Resources.right_3,
           Properties.Resources.right_4,
           Properties.Resources.right_5
        };


        public Image[] Up
        {
            get { return up; }
        }

        public Image[] Down
        {
            get { return down; }
        }

        public Image[] Left
        {
            get { return left; }
        }

        public Image[] Right
        {
            get { return right; }
        }
    }
}
