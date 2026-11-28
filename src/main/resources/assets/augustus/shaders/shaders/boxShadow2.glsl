#version 120

uniform sampler2D texture;
uniform vec2 texelSize;
uniform vec2 direction;
uniform float radius;
uniform vec3 startColor;

varying vec2 uv;

void main() {
    // Box Blur für Shadow mit Custom Color
    float totalAlpha = 0.0;
    int samples = 0;

    // Box filter
    for (float i = -radius; i <= radius; i += 1.0) {
        vec4 smpl = texture2D(texture, uv + direction * i * texelSize);
        totalAlpha += smpl.a;
        samples++;
    }

    if (samples > 0) {
        totalAlpha /= float(samples);
    }

    gl_FragColor = vec4(startColor, totalAlpha);
}
