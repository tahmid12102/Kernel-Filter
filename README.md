## Abstract
The following program is an image-processing library that applies various *kernel filters* to images. These filters include the identity, gaussian blur, sharpen, laplacian, emboss, and motion blur filters. Kernel filter modify pixels by replacing each pixel with a linear combination of its neighboring pixels. Kernel filters are widely used for image processing in applications such as Photoshop or GIMP. They are also used in convolution neural networks to extract features from an image and in digital cameras to remove camera shake.

## API

**private static Picture applyFilter(Picture picture, double [][] filter)** - Returns a new picture that applies the given input filter to the input picture.

**public static Picture identity (Picture picture)** - Returns a new picture that applies the identity filter to the given picture.

**public static Picture gaussian(Picture picutre)** -  Returns a new picture that applies a Gaussian blur filter to the given picture.

**public static Picture sharpen(Picture picture)** -  Returns a new picture that applies a sharpen filter to the given picture.

**public static Picture laplacian(Picture picture)** - Returns a new picture that applies an Laplacian filter to the given picture.

**public static Picture emboss(Picture picture)** - Returns a new picture that applies an emboss filter to the given picture.

**public static Picture motionBlur(Picture picture)** - Returns a new picture that applies a motion blur filter to the given picture.

**public static void main(String[] args)** - Test client. Takes a picture file name as a command line argument and applies all 6 kernel filters to original image and displays the filtered pictures in windows on the screen.

## Comments

 **Periodic boundary conditions** - When applying a kernel filter to a pixel near the boundary, some of its neighboring pixels may not exist. In such cases, we assume the leftmost column wraps around to the rightmost column and the top row wraps around to the bottom row (and vice versa).

 **Rounding** - When applying a kernel filter, the resulting RGB components may become fractional if the kernel weights are fractional. We will round each RGB component to the nearest integer, with ties rounding up.

 **Clamping** - When applying a kernel filter, the resulting RGB components may not remain between 0 and 255. If an RGB component of a pixel is less than 0, set it to 0; if is greater than 255, set it to 255. This mechanism for handling arithmetic overflow and underflow is known as clamping or saturating arithmetic.

 **Performance requirement** -  All methods take time proportional to the product of the number of pixels in the image and the number of elements in the kernel.

