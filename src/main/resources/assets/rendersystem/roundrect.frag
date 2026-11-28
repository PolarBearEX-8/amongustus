#version 120

/**
 * Rounded Rectangle Shader with Distance Field Antialiasing
 * Perfect smooth edges at any resolution
 */

uniform vec2 size;          // Full size of rectangle (width, height)
uniform float radius;       // Corner radius (used when cornerRadius is not set)
uniform vec4 cornerRadius;  // Individual corner radii (topLeft, topRight, bottomRight, bottomLeft)
uniform vec2 color_rg;      // RG components of RGBA color
uniform vec2 color_ba;      // BA components of RGBA color
uniform vec2 position;      // Position of rectangle (x, y)
uniform int outline;        // 1 = outline only, 0 = filled
uniform float lineWidth;    // Line width for outline mode

/**
 * Signed Distance Function for rounded rectangle
 * Returns distance from point p to the edge of the shape
 * Negative = inside, Positive = outside, 0 = on edge
 */
float sdRoundRect(vec2 p, vec2 b, float r) {
    vec2 q = abs(p) - b + r;
    return min(max(q.x, q.y), 0.0) + length(max(q, 0.0)) - r;
}

/**
 * Signed Distance Function for rounded rectangle with individual corner radii
 * Supports different radius for each corner
 * cornerRadii: vec4(topLeft, topRight, bottomRight, bottomLeft)
 */
float sdRoundRectCorners(vec2 p, vec2 b, vec4 cornerRadii) {
    // Determine which quadrant we're in and select appropriate radius
    float r = cornerRadii.x; // top-left by default

    if (p.x > 0.0 && p.y > 0.0) {
        r = cornerRadii.z; // bottom-right
    } else if (p.x > 0.0 && p.y <= 0.0) {
        r = cornerRadii.y; // top-right
    } else if (p.x <= 0.0 && p.y > 0.0) {
        r = cornerRadii.w; // bottom-left
    }

    // Standard rounded rect SDF with selected radius
    vec2 q = abs(p) - b + r;
    return min(max(q.x, q.y), 0.0) + length(max(q, 0.0)) - r;
}

/**
 * Antialiased step function using smoothstep
 * Creates smooth transitions at shape edges
 */
float aastep(float threshold, float value) {
    float afwidth = 0.7; // Fixed antialiasing width
    return smoothstep(threshold - afwidth, threshold + afwidth, value);
}

void main() {
    // Get fragment coordinates from texture coordinates
    vec2 uv = gl_TexCoord[0].xy;

    // Convert UV [0,1] to actual pixel coordinates
    vec2 pixelPos = uv * size;

    // Calculate center of the rectangle
    vec2 center = size * 0.5;

    // Calculate position relative to center
    vec2 p = pixelPos - center;

    // Half-size for the SDF calculation
    vec2 halfSize = size * 0.5;

    // Calculate distance to shape
    // Use individual corner radii if set (check if any is non-zero), otherwise use uniform radius
    float dist;
    if (cornerRadius.x > 0.0 || cornerRadius.y > 0.0 || cornerRadius.z > 0.0 || cornerRadius.w > 0.0) {
        dist = sdRoundRectCorners(p, halfSize, cornerRadius);
    } else {
        dist = sdRoundRect(p, halfSize, radius);
    }

    float alpha;

    if (outline == 1) {
        // Outline mode: alpha is 1 where distance is near lineWidth/2
        float outerDist = dist;
        float innerDist = dist + lineWidth;
        alpha = aastep(0.0, -outerDist) - aastep(0.0, -innerDist);
    } else {
        // Filled mode: alpha is 1 inside the shape
        alpha = aastep(0.0, -dist);
    }

    // Reconstruct color from two vec2 uniforms
    vec4 color = vec4(color_rg.x, color_rg.y, color_ba.x, color_ba.y);

    // Apply color with calculated alpha
    gl_FragColor = vec4(color.rgb, color.a * alpha);
}
