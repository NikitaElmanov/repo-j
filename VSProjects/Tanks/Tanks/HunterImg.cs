using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace Tanks
{
    public class HunterImg
    {
        Image[] up = new Image[] 
        { 
           Properties.Resources.hunter_up_1,
           Properties.Resources.hunter_up_2,
           Properties.Resources.hunter_up_3,
           Properties.Resources.hunter_up_4,
           Properties.Resources.hunter_up_5
        };

        Image[] down = new Image[] 
        { 
           Properties.Resources.hunter_down_1,
           Properties.Resources.hunter_down_2,
           Properties.Resources.hunter_down_3,
           Properties.Resources.hunter_down_4,
           Properties.Resources.hunter_down_5
        };

        Image[] left = new Image[] 
        { 
           Properties.Resources.hunter_left_1,
           Properties.Resources.hunter_left_2,
           Properties.Resources.hunter_left_3,
           Properties.Resources.hunter_left_4,
           Properties.Resources.hunter_left_5
        };

        Image[] right = new Image[] 
        { 
           Properties.Resources.hunter_right_1,
           Properties.Resources.hunter_right_2,
           Properties.Resources.hunter_right_3,
           Properties.Resources.hunter_right_4,
           Properties.Resources.hunter_right_5
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
