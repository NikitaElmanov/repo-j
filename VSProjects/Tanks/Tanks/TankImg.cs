using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace Tanks
{
    public class TankImg
    {
        Image[] up = new Image[] 
        { 
           Properties.Resources.tank_Up_1,
           Properties.Resources.tank_Up_2,
           Properties.Resources.tank_Up_3,
           Properties.Resources.tank_Up_4,
           Properties.Resources.tank_Up_5
        };

        Image[] down = new Image[] 
        { 
           Properties.Resources.tank_Down_1,
           Properties.Resources.tank_Down_2,
           Properties.Resources.tank_Down_3,
           Properties.Resources.tank_Down_4,
           Properties.Resources.tank_Down_5
        };

        Image[] left = new Image[] 
        { 
           Properties.Resources.tank_Left_1,
           Properties.Resources.tank_Left_2,
           Properties.Resources.tank_Left_3,
           Properties.Resources.tank_Left_4,
           Properties.Resources.tank_Left_5
        };

        Image[] right = new Image[] 
        { 
           Properties.Resources.tank_Right_1,
           Properties.Resources.tank_Right_2,
           Properties.Resources.tank_Right_3,
           Properties.Resources.tank_Right_4,
           Properties.Resources.tank_Right_5
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
