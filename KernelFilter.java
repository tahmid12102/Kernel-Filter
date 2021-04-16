import java.awt.Color;

public class KernelFilter{

    //Returns a new picture that applies the given input filter to the input picture.
    private static Picture applyFilter(Picture picture, double [][] filter){
        int width = picture.width();
        int height = picture.height();
        Picture filteredPicture = new Picture(width,height);
        int halfLength = filter[0].length/2;
        //Double for loop to traverse through input picture.
        for(int row = 0; row < height; row++){
            for (int col = 0; col < width; col++){
                double rNew = 0.0;
                double gNew = 0.0;
                double bNew = 0.0;
                //Double for loop to compute linear combination of neighboring pixels using filter.
                for(int i = -halfLength; i <= halfLength; i++){
                    for(int j = -halfLength; j <= halfLength; j++){
                        Color color = picture.get((col+i+width)%width,(row+j+height)%height); //Use modulus to handle boundary conditions by wrapping around. 
                        int r = color.getRed();
                        int g = color.getGreen();
                        int b = color.getBlue();
                        rNew += r * filter[i+halfLength][j+halfLength];
                        gNew += g * filter[i+halfLength][j+halfLength];
                        bNew += b * filter[i+halfLength][j+halfLength];
                    }
                }
                // Rounding - Resulting RGB components may be fractional. Therefore must round each RGB component to nearest interger.
                int R = (int)Math.round(rNew);
                int G = (int)Math.round(gNew);
                int B = (int)Math.round(bNew);
                // Clamping - If resulting RGB components are greater than 255, set it to 255. If less than 0, set it to 0.
                if (R > 255) R = 255; if (G > 255) G = 255; if (B > 255) B = 255;
                if (R < 0) R = 0; if (G < 0) G = 0; if (B < 0) B = 0;
                Color newColor = new Color(R,G,B);
                filteredPicture.set(col,row,newColor);
            }
        }
        return filteredPicture;
    }

    //Returns a new picture that applies the identity filter to the given picture.
    public static Picture identity(Picture picture){
        return picture;
    }

    //Returns a new picture that applies a Gaussian blur filter to the given picture.
    public static Picture gaussian(Picture picture){
        double [][] filter = {  {0.0625,0.125,0.0625},
                                {0.125,0.25,0.125},
                                {0.0625,0.125,0.0625}  };
        return applyFilter(picture, filter);
    }

    //Returns a new picture that applies a sharpen filter to the given picture.
    public static Picture sharpen(Picture picture){
        double [][] filter = {  {0,-1,0},
                                {-1,5,-1},
                                {0,-1,0}  };
        return applyFilter(picture,filter);
    }

    //Returns a new picture that applies an Laplacian filter to the given picture.
    public static Picture laplacian(Picture picture){
        double [][] filter = {  {-1,-1,-1},
                                {-1,8,-1},
                                {-1,-1,-1}  };
        return applyFilter(picture,filter);
    }

    //Returns a new picture that applies an emboss filter to the given picture.
    public static Picture emboss(Picture picture){
        double [][] filter = {  {-2,-1,0},
                                {-1,1,1},
                                {0,1,2}  };
        return applyFilter(picture,filter);
    }

    //Returns a new picture that applies a motion blur filter to the given picture.    
    public static Picture motionBlur(Picture picture){
        double [][] filter = {  {(double)1/9,0,0,0,0,0,0,0,0},
                                {0,(double)1/9,0,0,0,0,0,0,0},
                                {0,0,(double)1/9,0,0,0,0,0,0},
                                {0,0,0,(double)1/9,0,0,0,0,0},
                                {0,0,0,0,(double)1/9,0,0,0,0},
                                {0,0,0,0,0,(double)1/9,0,0,0},
                                {0,0,0,0,0,0,(double)1/9,0,0},
                                {0,0,0,0,0,0,0,(double)1/9,0},
                                {0,0,0,0,0,0,0,0,(double)1/9} };
        return applyFilter(picture,filter);
    }

    /*Test client. Takes a picture file name as a command line argument and applies all 6 kernel filters 
    to original image and displays the filtered pictures in windows on the screen.*/
    public static void main(String[] args){
        Picture originalPic = new Picture(args[0]);
        
        Picture identityPic = identity(originalPic);
        identityPic.show();

        Picture gaussianPic = gaussian(originalPic);
        gaussianPic.show();

        Picture sharpenPic = sharpen(originalPic);
        sharpenPic.show();

        Picture laplacianPic = laplacian(originalPic);
        laplacianPic.show();

        Picture embossPic = emboss(originalPic);
        embossPic.show();

        Picture motionBlurPic = motionBlur(originalPic);
        motionBlurPic.show();

    }
}